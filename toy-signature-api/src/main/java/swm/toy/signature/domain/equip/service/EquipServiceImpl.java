package swm.toy.signature.domain.equip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.entity.EquipEntity;
import swm.toy.signature.domain.equip.repository.EquipRepository;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class EquipServiceImpl implements EquipService {
    private final EquipRepository equipRepository;

    @Transactional
    @Override
    public EquipDto createEquip(EquipDto equip, UserDto.Auth authUser) {
        UserEntity author = UserEntity.builder()
                .id(authUser.getId())
                .name(authUser.getName())
                .bio(authUser.getBio())
                .image(authUser.getImage())
                .build();

        EquipEntity equipEntity = EquipEntity.builder()
                .author(author)
                .build();

        equipEntity = equipRepository.save(equipEntity);
        return convertEntityToDto(equipEntity);
    }

    private EquipDto convertEntityToDto(EquipEntity entity) {
        return EquipDto.builder()
                .build();
    }
}
