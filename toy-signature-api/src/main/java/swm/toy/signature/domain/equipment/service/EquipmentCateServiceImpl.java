package swm.toy.signature.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.base.exception.AppException;
import swm.toy.signature.base.exception.Error;
import swm.toy.signature.domain.equipment.dto.EquipmentCateDto;
import swm.toy.signature.domain.equipment.entity.EquipmentCateEntity;
import swm.toy.signature.domain.equipment.repository.EquipmentCateRepository;
import swm.toy.signature.domain.user.dto.UserDto;

@Service
@RequiredArgsConstructor
public class EquipmentCateServiceImpl implements EquipmentCateService {
    private final EquipmentCateRepository equipmentCateRepository;

    @Transactional
    @Override
    public EquipmentCateDto createEquipmentCate(EquipmentCateDto equipCode, UserDto.Auth authUser) {
        EquipmentCateEntity entity = EquipmentCateEntity.builder()
                .name(equipCode.getName())
                .build();

        entity = equipmentCateRepository.save(entity);
        return convertEntityToDto(entity);
    }

    @Transactional
    @Override
    public EquipmentCateDto updateEquipmentCate(EquipmentCateDto.Update equipmentCate, UserDto.Auth authUser) {
        EquipmentCateEntity entity = equipmentCateRepository.findById(equipmentCate.getId()).orElseThrow(() -> new AppException(Error.EQUIPMENT_CATE_NOT_FOUND));
        if (equipmentCate.getName() != null) {
            entity.setName(equipmentCate.getName());
        }

        EquipmentCateEntity save = equipmentCateRepository.save(entity);

        return convertEntityToDto(save);
    }

    private EquipmentCateDto convertEntityToDto(EquipmentCateEntity entity) {
        return EquipmentCateDto.builder()
                .name(entity.getName())
                .build();
    }
}
