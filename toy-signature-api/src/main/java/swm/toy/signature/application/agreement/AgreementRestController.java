package swm.toy.signature.application.agreement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.application.item.ItemModel;
import swm.toy.signature.application.item.ItemPutRequestDTO;
import swm.toy.signature.application.item.MultipleItemModel;
import swm.toy.signature.domain.agreement.AgreementService;
import swm.toy.signature.domain.item.ItemService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

@RestController
public class AgreementRestController {

    private final AgreementService agreementService;

    AgreementRestController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements")
    public AgreementModel postItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody AgreementPostRequestDTO dto) {
        var agreementCreated = agreementService.createAgreement(jwtPayload.getUserId(), dto.toAgreementCreateRequest());
        return AgreementModel.from(agreementCreated);
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
    public ItemModel putItem(@AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody ItemPutRequestDTO dto) {
        final var itemUpdated = itemService.updateItem(jwtPayload.getUserId(), dto.toUpdateRequest());
        return ItemModel.fromItem(itemUpdated);
    }
}
