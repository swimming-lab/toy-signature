package swm.toy.signature.domain.equipment.service;


import swm.toy.signature.domain.equipment.dto.EquipmentDto;
import swm.toy.signature.domain.user.dto.UserDto;

public interface EquipmentService {
    EquipmentDto createEquip(final EquipmentDto equip, final UserDto.Auth authUser);
}
