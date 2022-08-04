package swm.toy.signature.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.equipment.dto.EquipmentDto;
import swm.toy.signature.domain.equipment.entity.EquipmentEntity;
import swm.toy.signature.domain.equipment.repository.EquipmentRepository;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Transactional
    @Override
    public EquipmentDto createEquip(EquipmentDto equip, UserDto.Auth authUser) {
        UserEntity author = UserEntity.builder()
                .id(authUser.getId())
                .name(authUser.getName())
                .bio(authUser.getBio())
                .image(authUser.getImage())
                .build();

        EquipmentEntity equipmentEntity = EquipmentEntity.builder()
                .author(author)
                .build();

        equipmentEntity = equipmentRepository.save(equipmentEntity);
        return convertEntityToDto(equipmentEntity);
    }

    private EquipmentDto convertEntityToDto(EquipmentEntity entity) {
        return EquipmentDto.builder()
                .build();
    }
}
