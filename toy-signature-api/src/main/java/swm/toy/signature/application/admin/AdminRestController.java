package swm.toy.signature.application.admin;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.brand.ItemBrandService;
import swm.toy.signature.domain.item.type.ItemType;
import swm.toy.signature.domain.item.type.ItemTypeService;
import swm.toy.signature.domain.jwt.JWTSerializer;
import swm.toy.signature.domain.user.UserService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

@RequestMapping("/admin")
@RestController
public class AdminRestController {

    private final JWTSerializer jwtSerializer;
    private final UserService userService;
    private final ItemTypeService itemTypeService;
    private final ItemBrandService itemBrandService;

    AdminRestController(
            UserService userService,
            ItemTypeService itemTypeService,
            ItemBrandService itemBrandService,
            JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.itemTypeService = itemTypeService;
        this.itemBrandService = itemBrandService;
        this.jwtSerializer = jwtSerializer;
    }

    @Operation(summary = "Admin api sample")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    public String getSomething(@AuthenticationPrincipal UserJWTPayload jwtPayload) {
        return "This is Admin api sample";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/item/type")
    public ItemType postItemType(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemTypeRequestDTO dto) {
        return itemTypeService.createItemType(dto.getType(), dto.getHeavy());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/item/type")
    public ItemType putItemType(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemTypeRequestDTO.Update dto) {
        return itemTypeService.updateItemType(dto.getId(), dto.getType(), dto.getHeavy());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/item/brand")
    public ItemBrand postItemBrand(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemBrandRequestDTO dto) {
        return itemBrandService.createItemBrand(dto.getBrandName());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/item/brand")
    public ItemBrand putItemBrand(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemBrandRequestDTO.Update dto) {
        return itemBrandService.updateItemBrand(dto.getId(), dto.getBrandName());
    }
}
