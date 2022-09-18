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
public class ItemCommandController {

    private final ItemService itemService;

    ItemCommandController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ItemModel postItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody ItemPostParam param) {
        final var itemCreated = itemService.createItem(jwtPayload.getUserId(), param.toItemCreateRequest());
        return ItemModel.from(itemCreated);
    }

    @PutMapping("/items")
    public ItemModel putItem(@AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody ItemPutParam param) {
        final var itemUpdated = itemService.updateItem(jwtPayload.getUserId(), param.toUpdateRequest());
        return ItemModel.from(itemUpdated);
    }
}
