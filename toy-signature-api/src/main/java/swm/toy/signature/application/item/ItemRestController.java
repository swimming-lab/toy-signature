package swm.toy.signature.application.item;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.domain.jwt.JWTSerializer;
import swm.toy.signature.domain.user.UserService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RestController
public class ItemRestController {

    private final JWTSerializer jwtSerializer;
    private final UserService userService;
    private final ItemService itemService;

    ItemRestController(
            JWTSerializer jwtSerializer, UserService userService, ItemService itemService) {
        this.jwtSerializer = jwtSerializer;
        this.userService = userService;
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ItemModel postItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @Valid @RequestBody ItemPostRequestDTO dto) {
        var itemCreated = itemService.createItem(jwtPayload.getUserId(), dto.toItemCreateRequest());
        return ItemModel.fromItem(itemCreated);
    }

    @GetMapping(value = "/items")
    public MultipleItemModel getItems(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(jwtPayload.getUserId(), pageable);
        return MultipleItemModel.fromItems(items);
    }

    @GetMapping(
            value = "/items",
            params = {"author"})
    public MultipleItemModel getItemsByAuthor(@RequestParam String authorId, Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(Long.valueOf(authorId), pageable);
        return MultipleItemModel.fromItems(items);
    }

    @PutMapping("/items")
    public ItemModel putItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @RequestBody ItemPutRequestDTO dto) {
        final var itemUpdated =
                itemService.updateItem(jwtPayload.getUserId(), dto.getId(), dto.toUpdateRequest());
        return ItemModel.fromItem(itemUpdated);
    }

    //    @GetMapping("/item/type")
    //    public EquipBrandDto.Multiple getItemType() {
    //        return new EquipBrandDto.Multiple(itemService.get());
    //    }
    //
    //    @GetMapping("/item/brand")
    //    public EquipTypeDto.Multiple getEquipType() {
    //        return new EquipTypeDto.Multiple(itemService.getEquipType());
    //    }
}
