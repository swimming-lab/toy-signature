package swm.toy.signature.domain.equip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.equip.service.EquipBrandService;
import swm.toy.signature.domain.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/equipBrand")
@RequiredArgsConstructor
public class EquipBrandController {
    private final EquipBrandService equipBrandService;

    @GetMapping()
    public EquipBrandDto.Multiple getEquipType() {
        return new EquipBrandDto.Multiple(equipBrandService.getEquipBrand());
    }

    @PostMapping()
    public EquipBrandDto.Single<EquipBrandDto> createEquipType(
            @Valid @RequestBody EquipBrandDto.Single<EquipBrandDto> equipBrand,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipBrandDto.Single<>(equipBrandService.createEquipBrand(equipBrand.getEquipBrand(), authUser));
    }

    @PutMapping()
    public EquipBrandDto.Single<EquipBrandDto> updateEquipType(
            @Valid @RequestBody EquipBrandDto.Single<EquipBrandDto.Update> equipBrand,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipBrandDto.Single<>(equipBrandService.updateEquipBrand(equipBrand.getEquipBrand(), authUser));
    }
}
