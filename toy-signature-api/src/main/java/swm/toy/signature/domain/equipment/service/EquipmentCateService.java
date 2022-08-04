package swm.toy.signature.domain.equipment.service;


import swm.toy.signature.domain.equipment.dto.EquipmentCateDto;
import swm.toy.signature.domain.user.dto.UserDto;

public interface EquipmentCateService {
    EquipmentCateDto createEquipmentCate(final EquipmentCateDto equipCode, final UserDto.Auth authUser);

    EquipmentCateDto updateEquipmentCate(EquipmentCateDto.Update equipmentCate, UserDto.Auth authUser);
}
