package swm.toy.signature.domain.equip.service;


import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.List;

public interface EquipTypeService {

    EquipTypeDto createEquipType(final EquipTypeDto equipType, final UserDto.Auth authUser);

    EquipTypeDto updateEquipType(final EquipTypeDto.Update equipType, final UserDto.Auth authUser);

    List<EquipTypeDto> getEquipType();
}
