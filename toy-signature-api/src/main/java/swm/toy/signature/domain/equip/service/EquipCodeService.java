package swm.toy.signature.domain.equip.service;


import swm.toy.signature.domain.equip.dto.EquipCodeDto;
import swm.toy.signature.domain.user.dto.UserDto;

public interface EquipCodeService {
    EquipCodeDto createEquipCode(final EquipCodeDto equipCode, final UserDto.Auth authUser);
}
