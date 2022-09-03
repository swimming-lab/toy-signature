package swm.toy.signature.application.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

@RestController
public class ItemRestController {

    private final ItemService itemService;

    ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ItemModel postItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemPostParam param) {
        final var itemCreated = itemService.createItem(jwtPayload.getUserId(), param.toItemCreateRequest());
        return ItemModel.from(itemCreated);
    }

    @GetMapping(value = "/items")
    public MultipleItemModel getItems(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(jwtPayload.getUserId(), pageable);
        return MultipleItemModel.from(items);
    }

    @GetMapping(
            value = "/items",
            params = {"author"})
    public MultipleItemModel getItemsByAuthor(
            @RequestParam String authorId,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(Long.valueOf(authorId), pageable);
        return MultipleItemModel.from(items);
    }

    @PutMapping("/items")
    public ItemModel putItem(@AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody ItemPutParam param) {
        final var itemUpdated = itemService.updateItem(jwtPayload.getUserId(), param.toUpdateRequest());
        return ItemModel.from(itemUpdated);
    }
}
