package swm.toy.signature.application.item.itemBrand;

import lombok.Value;
import swm.toy.signature.domain.item.brand.ItemBrand;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleItemBrandModel {

    List<ItemBrandModel> itemBrands;
    int itemBrandCount;

    public static MultipleItemBrandModel fromItemBrands(List<ItemBrand> itemBrands) {
        final var itemBrandCollected =
                itemBrands.stream().map(ItemBrandModel::from).collect(toList());
        return new MultipleItemBrandModel(itemBrandCollected, itemBrandCollected.size());
    }
}
