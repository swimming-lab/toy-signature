package swm.toy.signature.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.equipment.dto.EquipmentDto;
import swm.toy.signature.domain.equipment.dto.EquipmentCateDto;
import swm.toy.signature.domain.equipment.service.EquipmentService;
import swm.toy.signature.domain.equipment.service.EquipmentCateService;
import swm.toy.signature.domain.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final EquipmentCateService equipCodeService;

    @PostMapping
    public EquipmentDto.SingleEquip<EquipmentDto> createEquip(
            @Valid @RequestBody EquipmentDto.SingleEquip<EquipmentDto> equip,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipmentDto.SingleEquip<>(equipmentService.createEquip(equip.getEquip(), authUser));
    }

    @PostMapping("/cate")
    public EquipmentCateDto.Single<EquipmentCateDto> createEquipmentCate(
            @Valid @RequestBody EquipmentCateDto.Single<EquipmentCateDto> equipmentCate,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipmentCateDto.Single<>(equipCodeService.createEquipmentCate(equipmentCate.getEquipmentCate(), authUser));
    }

    @PutMapping("/cate")
    public EquipmentCateDto.Single<EquipmentCateDto> updateEquipmentCate(
            @Valid @RequestBody EquipmentCateDto.Single<EquipmentCateDto.Update> equipmentCate,
            @AuthenticationPrincipal UserDto.Auth authUser) {
        return new EquipmentCateDto.Single<>(equipCodeService.updateEquipmentCate(equipmentCate.getEquipmentCate(), authUser));
    }
}
