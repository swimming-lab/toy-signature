package swm.toy.signature.application.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.application.common.ResponseModel;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RestController
public class ItemQueryController {

    private final ItemService itemService;

    ItemQueryController(ItemService itemService) {
        this.itemService = itemService;
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

    @GetMapping(value = "/items/count")
    public ResponseEntity getItemsCount(
            @AuthenticationPrincipal UserJWTPayload jwtPayload) {
        final var count = itemService.getItemsCountByAuthorId(jwtPayload.getUserId());
        return ResponseEntity.ok(ResponseModel.response("count", count));
    }
}
