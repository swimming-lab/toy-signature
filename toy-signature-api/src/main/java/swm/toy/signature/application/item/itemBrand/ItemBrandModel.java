package swm.toy.signature.application.item.itemBrand;

import lombok.Value;
import swm.toy.signature.domain.item.brand.ItemBrand;

@Value
public class ItemBrandModel {

    ItemBrandModelNested itemBrand;

    public static ItemBrandModel from(ItemBrand itemBrand) {
        return new ItemBrandModel(ItemBrandModelNested.from(itemBrand));
    }

    @Value
    public static class ItemBrandModelNested {
        Long id;
        String brandName;

        public static ItemBrandModelNested from(ItemBrand itemBrand) {
            return new ItemBrandModelNested(itemBrand.getId(), itemBrand.getBrandName());
        }
    }
}
