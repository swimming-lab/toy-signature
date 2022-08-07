package swm.toy.signature.domain.equip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.base.exception.AppException;
import swm.toy.signature.base.exception.Error;
import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.equip.repository.EquipTypeRepository;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipTypeServiceImpl implements EquipTypeService {
    private final EquipTypeRepository equipTypeRepository;

    @Transactional
    @Override
    public EquipTypeDto createEquipType(EquipTypeDto equipType, UserDto.Auth authUser) {
        EquipTypeEntity entity = EquipTypeEntity.builder()
                .type(equipType.getType())
                .heavy(equipType.getHeavy())
                .build();

        equipTypeRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional
    @Override
    public EquipTypeDto updateEquipType(EquipTypeDto.Update equipType, UserDto.Auth authUser) {
        EquipTypeEntity entity = equipTypeRepository.findById(equipType.getId()).orElseThrow(() -> new AppException(Error.EQUIP_TYPE_NOT_FOUND));
        if (equipType.getType() != null) {
            entity.setType(equipType.getType());
        }

        if (equipType.getHeavy() != null) {
            entity.setHeavy(equipType.getHeavy());
        }

        equipTypeRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EquipTypeDto> getEquipType() {
//        Sort.by(Sort.Direction.DESC, "vDate");
        return equipTypeRepository.findAll().stream().map(entity -> {
            return convertEntityToDto(entity);
        }).collect(Collectors.toList());
    }

    private EquipTypeDto convertEntityToDto(EquipTypeEntity entity) {
        return EquipTypeDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .heavy(entity.getHeavy())
                .build();
    }
}
