package swm.toy.signature.domain.equip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.base.exception.AppException;
import swm.toy.signature.base.exception.Error;
import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.entity.EquipEntity;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.equip.model.EquipQueryParam;
import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
import swm.toy.signature.domain.equip.repository.EquipRepository;
import swm.toy.signature.domain.equip.repository.EquipTypeRepository;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipServiceImpl implements EquipService {
    private final EquipRepository equipRepository;
    private final EquipTypeRepository equipTypeRepository;
    private final EquipBrandRepository equipBrandRepository;

    @Transactional
    @Override
    public EquipDto createEquip(EquipDto equip, UserDto.Auth authUser) {
        UserEntity author = UserEntity.builder()
                .id(authUser.getId())
                .name(authUser.getName())
                .build();

        EquipTypeEntity equipType = equipTypeRepository.findById(equip.getEquipType().getId())
                .orElseThrow(() -> new AppException(Error.EQUIP_TYPE_NOT_FOUND));
        EquipBrandEntity equipBrand = equipBrandRepository.findById(equip.getEquipBrand().getId())
                .orElseThrow(() -> new AppException(Error.EQUIP_BRAND_NOT_FOUND));

        EquipEntity entity = EquipEntity.builder()
                .licensePlate(equip.getLicensePlate())
                .sequence(equip.getSequence())
                .insuranceYn(equip.getInsuranceYn())
                .routineYn(equip.getRoutineYn())
                .equipType(equipType)
                .equipBrand(equipBrand)
                .author(author)
                .build();

        equipRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional
    @Override
    public EquipDto updateEquip(EquipDto.Update equip, UserDto.Auth authUser) {
        EquipEntity entity = equipRepository.findById(equip.getId()).orElseThrow(() -> new AppException(Error.EQUIP_NOT_FOUND));
        if (equip.getLicensePlate() != null) {
            entity.setLicensePlate(equip.getLicensePlate());
        }

        if (equip.getSequence() != null) {
            entity.setSequence(equip.getSequence());
        }

        if (equip.getInsuranceYn() != null) {
            entity.setInsuranceYn(equip.getInsuranceYn());
        }

        if (equip.getRoutineYn() != null) {
            entity.setRoutineYn(equip.getRoutineYn());
        }

        if (equip.getEquipType() != null) {
            EquipTypeDto.Update equipTypeDto = equip.getEquipType();
            EquipTypeEntity equipTypeEntity = equipTypeRepository.findById(equipTypeDto.getId()).orElseThrow(() -> new AppException(Error.EQUIP_TYPE_NOT_FOUND));

            entity.setEquipType(equipTypeEntity);
        }

        if (equip.getEquipBrand() != null) {
            EquipBrandDto.Update equipBrandDto = equip.getEquipBrand();
            EquipBrandEntity equipBrandEntity = equipBrandRepository.findById(equipBrandDto.getId())
                    .orElseThrow(() -> new AppException(Error.EQUIP_BRAND_NOT_FOUND));

            entity.setEquipBrand(equipBrandEntity);
        }

        equipRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EquipDto> getEquips(EquipQueryParam equipQueryParam, UserDto.Auth authUser) {
        Pageable pageable = null;
        if (equipQueryParam.getOffset() != null) {
            pageable = PageRequest.of(equipQueryParam.getOffset(), equipQueryParam.getLimit());
        }

        return equipRepository.findByAutherIdOrderBySequenceAsc(authUser.getId(), pageable).stream().map(entity -> {
            return convertEntityToDto(entity);
        }).collect(Collectors.toList());
    }

    private EquipDto convertEntityToDto(EquipEntity entity) {
        return EquipDto.builder()
                .id(entity.getId())
                .licensePlate(entity.getLicensePlate())
                .sequence(entity.getSequence())
                .insuranceYn(entity.getInsuranceYn())
                .routineYn(entity.getRoutineYn())
                .equipType(EquipTypeDto.builder()
                        .type(entity.getEquipType().getType())
                        .heavy(entity.getEquipType().getHeavy())
                        .build())
                .equipBrand(EquipBrandDto.builder()
                        .brandName(entity.getEquipBrand().getBrandName())
                        .build())
                .author(EquipDto.Author.builder()
                        .id(entity.getAuthor().getId())
                        .email(entity.getAuthor().getEmail())
                        .name(entity.getAuthor().getName())
                        .build())
                .build();
    }
}
