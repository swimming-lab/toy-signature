package swm.toy.signature.application.item.itemBrand;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.domain.item.brand.ItemBrandFindService;

@RestController
public class ItemBrandRestController {

    private final ItemBrandFindService itemBrandFindService;

    ItemBrandRestController(ItemBrandFindService itemBrandFindService) {
        this.itemBrandFindService = itemBrandFindService;
    }

    @GetMapping(value = "/item/brand")
    public MultipleItemBrandModel getItemBrands() {
        final var itemBrands = itemBrandFindService.findAll();
        return MultipleItemBrandModel.fromItemBrands(itemBrands);
    }
}
