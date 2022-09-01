package swm.toy.signature.application.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

import static swm.toy.signature.application.common.ResponseModel.response;

@RestController
public class ItemRestController {

    private final ItemService itemService;

    ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity postItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemPostParam param) {
        final var itemCreated = itemService.createItem(jwtPayload.getUserId(), param.toItemCreateRequest());
//        return ResponseEntity.ok(response("item", itemCreated));
        return ResponseEntity.ok(response("item", ItemModel.from(itemCreated)));
    }

    @GetMapping(value = "/items")
    public MultipleItemModel getItems(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(jwtPayload.getUserId(), pageable);
//        return ResponseEntity.ok(response("items", items));
        return MultipleItemModel.from(items);
    }

    @GetMapping(
            value = "/items",
            params = {"author"})
    public ResponseEntity getItemsByAuthor(
            @RequestParam String authorId,
            @PageableDefault(size = 20, sort = "contents.sequence", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        final var items = itemService.getItemsByAuthorId(Long.valueOf(authorId), pageable);
        return ResponseEntity.ok(response("items", items));
    }

    @PutMapping("/items")
    public ResponseEntity putItem(@AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody ItemPutParam param) {
        final var itemUpdated = itemService.updateItem(jwtPayload.getUserId(), param.toUpdateRequest());
        return ResponseEntity.ok(response("item", itemUpdated));
    }
}
