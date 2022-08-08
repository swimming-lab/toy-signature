package swm.toy.signature.domain.equip.service;


import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.model.EquipQueryParam;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.List;

public interface EquipService {

    EquipDto createEquip(final EquipDto equip, final UserDto.Auth authUser);

    EquipDto updateEquip(EquipDto.Update equip, UserDto.Auth authUser);

    List<EquipDto> getEquips(EquipQueryParam equipQueryParam, UserDto.Auth authUser);
}
