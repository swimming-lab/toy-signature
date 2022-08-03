package swm.toy.signature.domain.equip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.domain.equip.dto.EquipCodeDto;
import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.service.EquipCodeService;
import swm.toy.signature.domain.equip.service.EquipService;
import swm.toy.signature.domain.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/equips")
@RequiredArgsConstructor
public class EquipsController {
    private final EquipService equipService;
    private final EquipCodeService equipCodeService;

    @PostMapping
    public EquipDto.SingleEquip<EquipDto> createEquip(@Valid @RequestBody EquipDto.SingleEquip<EquipDto> equip, @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipDto.SingleEquip<>(equipService.createEquip(equip.getEquip(), authUser));
    }

    @PostMapping("/code")
    public EquipCodeDto.SingleEquipCode<EquipCodeDto> createEquipCode(@Valid @RequestBody EquipCodeDto.SingleEquipCode<EquipCodeDto> equipCode, @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipCodeDto.SingleEquipCode<>(equipCodeService.createEquipCode(equipCode.getEquip(), authUser));
    }
}
