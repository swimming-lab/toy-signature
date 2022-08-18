package swm.toy.signature.application.item.itemBrand;

import lombok.Value;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.application.item.itemBrand.ItemBrandModel.ItemBrandModelNested;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleItemBrandModel {

    List<ItemBrandModelNested> itemBrands;
    int itemBrandCount;

    static MultipleItemBrandModel fromItemBrands(List<ItemBrand> itemBrands) {
        final var itemBrandCollected = itemBrands.stream().map(ItemBrandModelNested::fromItemBrand).collect(toList());
        return new MultipleItemBrandModel(itemBrandCollected, itemBrandCollected.size());
    }
}
