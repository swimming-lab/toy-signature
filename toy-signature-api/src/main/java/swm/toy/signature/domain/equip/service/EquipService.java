package swm.toy.signature.domain.equip.service;


import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.user.dto.UserDto;

public interface EquipService {
    EquipDto createEquip(final EquipDto equip, final UserDto.Auth authUser);
}
