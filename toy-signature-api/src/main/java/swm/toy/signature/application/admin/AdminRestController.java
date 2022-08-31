package swm.toy.signature.application.admin;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
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

import static swm.toy.signature.application.common.ResponseModel.response;

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
    public ResponseEntity postItemType(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemTypeParam param) {
        final var itemType = itemTypeService.createItemType(param.getType(), param.getHeavy());
        return ResponseEntity.ok(response("itemType", itemType));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/item/type")
    public ResponseEntity putItemType(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemTypeParam.Update param) {
        final var itemType = itemTypeService.updateItemType(param.getId(), param.getType(), param.getHeavy());
        return ResponseEntity.ok(response("itemType", itemType));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/item/brand")
    public ResponseEntity postItemBrand(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemBrandParam param) {
        final var itemBrand = itemBrandService.createItemBrand(param.getBrandName());
        return ResponseEntity.ok(response("itemBrand", itemBrand));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/item/brand")
    public ResponseEntity putItemBrand(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemBrandParam.Update param) {
        final var itemBrand = itemBrandService.updateItemBrand(param.getId(), param.getBrandName());
        return ResponseEntity.ok(response("itemBrand", itemBrand));
    }
}
