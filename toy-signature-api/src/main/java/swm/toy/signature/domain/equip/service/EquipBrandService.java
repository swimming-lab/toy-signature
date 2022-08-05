package swm.toy.signature.domain.equip.service;


import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.List;

public interface EquipBrandService {
    EquipBrandDto createEquipBrand(final EquipBrandDto equipBrand, final UserDto.Auth authUser);

    EquipBrandDto updateEquipBrand(final EquipBrandDto.Update equipBrand, final UserDto.Auth authUser);

    List<EquipBrandDto> getEquipBrand();
}
