package swm.toy.signature.domain.equip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.model.EquipQueryParam;
import swm.toy.signature.domain.equip.service.EquipService;
import swm.toy.signature.domain.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/equip")
@RequiredArgsConstructor
public class EquipController {
    private final EquipService equipService;

    @GetMapping()
    public EquipDto.Multiple getEquip(@ModelAttribute @Valid EquipQueryParam equipQueryParam, @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipDto.Multiple(equipService.getEquips(equipQueryParam, authUser));
    }

    @PostMapping
    public EquipDto.Single<EquipDto> createEquip(
            @Valid @RequestBody EquipDto.Single<EquipDto> equip,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipDto.Single<>(equipService.createEquip(equip.getEquip(), authUser));
    }

    @PutMapping()
    public EquipDto.Single<EquipDto> updateEquip(
            @Valid @RequestBody EquipDto.Single<EquipDto.Update> equip,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipDto.Single<>(equipService.updateEquip(equip.getEquip(), authUser));
    }
}
