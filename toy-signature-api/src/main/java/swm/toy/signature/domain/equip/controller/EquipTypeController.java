package swm.toy.signature.domain.equip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.equip.service.EquipTypeService;
import swm.toy.signature.domain.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/equipType")
@RequiredArgsConstructor
public class EquipTypeController {
    private final EquipTypeService equipTypeService;

    @GetMapping()
    public EquipTypeDto.Multiple getEquipType() {
        return new EquipTypeDto.Multiple(equipTypeService.getEquipType());
    }

    @PostMapping()
    public EquipTypeDto.Single<EquipTypeDto> createEquipType(
            @Valid @RequestBody EquipTypeDto.Single<EquipTypeDto> equipType,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipTypeDto.Single<>(equipTypeService.createEquipType(equipType.getEquipType(), authUser));
    }

    @PutMapping()
    public EquipTypeDto.Single<EquipTypeDto> updateEquipType(
            @Valid @RequestBody EquipTypeDto.Single<EquipTypeDto.Update> equipType,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipTypeDto.Single<>(equipTypeService.updateEquipType(equipType.getEquipType(), authUser));
    }
}
