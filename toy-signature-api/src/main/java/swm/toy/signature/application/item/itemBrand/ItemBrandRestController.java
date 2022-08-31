package swm.toy.signature.application.item.itemBrand;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.domain.item.brand.ItemBrandFindService;

import static swm.toy.signature.application.common.ResponseModel.response;

@RestController
public class ItemBrandRestController {

    private final ItemBrandFindService itemBrandFindService;

    ItemBrandRestController(ItemBrandFindService itemBrandFindService) {
        this.itemBrandFindService = itemBrandFindService;
    }

    @GetMapping(value = "/item/brand")
    public ResponseEntity getItemBrands() {
        final var itemBrands = itemBrandFindService.findAll();
        return ResponseEntity.ok(response("itemBrands", itemBrands));
    }
}
