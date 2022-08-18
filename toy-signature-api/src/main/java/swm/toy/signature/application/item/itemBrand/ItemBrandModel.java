package swm.toy.signature.application.item.itemBrand;

import lombok.Value;
import swm.toy.signature.domain.item.brand.ItemBrand;

@Value
public class ItemBrandModel {

    ItemBrandModelNested itemBrand;

    static ItemBrandModel fromItemBrand(ItemBrand itemBrand) {
        return new ItemBrandModel(ItemBrandModelNested.fromItemBrand(itemBrand));
    }

    @Value
    static class ItemBrandModelNested {
        Long id;
        String brandName;

        static ItemBrandModelNested fromItemBrand(ItemBrand itemBrand) {
            return new ItemBrandModelNested(
                    itemBrand.getId(),
                    itemBrand.getBrandName());
        }
    }
}
