package swm.toy.signature.application.item;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RestController
public class ItemRestController {

    private final ItemService itemService;

    ItemRestController(ItemService itemService) {
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
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(jwtPayload.getUserId(), pageable);
        return MultipleItemModel.fromItems(items);
    }

    @GetMapping(
            value = "/items",
            params = {"author"})
    public MultipleItemModel getItemsByAuthor(
            @RequestParam String authorId,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(Long.valueOf(authorId), pageable);
        return MultipleItemModel.fromItems(items);
    }

    @PutMapping("/items")
    public ItemModel putItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @RequestBody ItemPutRequestDTO dto) {
        final var itemUpdated =
                itemService.updateItem(jwtPayload.getUserId(), dto.toUpdateRequest());
        return ItemModel.fromItem(itemUpdated);
    }
}
