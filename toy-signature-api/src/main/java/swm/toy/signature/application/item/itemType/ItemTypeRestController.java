package swm.toy.signature.application.item.itemType;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.domain.item.type.ItemTypeFindService;

@RestController
public class ItemTypeRestController {

    private final ItemTypeFindService itemTypeFindService;

    ItemTypeRestController(ItemTypeFindService itemTypeFindService) {
        this.itemTypeFindService = itemTypeFindService;
    }

    @GetMapping(value = "/item/type")
    public MultipleItemTypeModel getItemTypes() {
        final var itemTypes = itemTypeFindService.findAll();
        return MultipleItemTypeModel.fromItemTypes(itemTypes);
    }
}
