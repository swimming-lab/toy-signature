package swm.toy.signature.domain.equip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.equip.dto.EquipCodeDto;
import swm.toy.signature.domain.equip.entity.EquipCodeEntity;
import swm.toy.signature.domain.equip.repository.EquipCodeRepository;
import swm.toy.signature.domain.user.dto.UserDto;

@Service
@RequiredArgsConstructor
public class EquipCodeServiceImpl implements EquipCodeService {
    private final EquipCodeRepository equipCodeRepository;

    @Transactional
    @Override
    public EquipCodeDto createEquipCode(EquipCodeDto equipCode, UserDto.Auth authUser) {
        EquipCodeEntity equipCodeEntity = EquipCodeEntity.builder()
                .equipCode(equipCode.getCode())
                .equipModel(equipCode.getModel())
                .equipName(equipCode.getName())
                .build();

        equipCodeEntity = equipCodeRepository.save(equipCodeEntity);
        return convertEntityToDto(equipCodeEntity);
    }

    private EquipCodeDto convertEntityToDto(EquipCodeEntity entity) {
        return EquipCodeDto.builder()
                .code(entity.getEquipCode())
                .model(entity.getEquipModel())
                .name(entity.getEquipName())
                .build();
    }
}
