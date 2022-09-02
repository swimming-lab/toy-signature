package swm.toy.signature.application.item.itemType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.domain.item.type.ItemTypeFindService;

import static swm.toy.signature.application.common.ResponseModel.response;

@RestController
public class ItemTypeRestController {

    private final ItemTypeFindService itemTypeFindService;

    ItemTypeRestController(ItemTypeFindService itemTypeFindService) {
        this.itemTypeFindService = itemTypeFindService;
    }

    @GetMapping(value = "/item/type")
    public MultipleItemTypeModel getItemTypes() {
        final var itemTypes = itemTypeFindService.findAll();
        return MultipleItemTypeModel.from(itemTypes);
    }
}
