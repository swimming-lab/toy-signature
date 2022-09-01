package swm.toy.signature.application.item.itemBrand;

import lombok.Value;
import swm.toy.signature.domain.item.brand.ItemBrand;

@Value
public class ItemBrandModel {

    Long id;
    String brandName;

    public static ItemBrandModel from(ItemBrand itemBrand) {
        return new ItemBrandModel(itemBrand.getId(), itemBrand.getBrandName());
    }
}
