package swm.toy.signature.application.item.itemBrand;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.Value;
import swm.toy.signature.application.item.itemBrand.ItemBrandModel.ItemBrandModelNested;
import swm.toy.signature.domain.item.brand.ItemBrand;

@Value
public class MultipleItemBrandModel {

    List<ItemBrandModelNested> itemBrands;
    int itemBrandCount;

    static MultipleItemBrandModel fromItemBrands(List<ItemBrand> itemBrands) {
        final var itemBrandCollected =
                itemBrands.stream().map(ItemBrandModelNested::fromItemBrand).collect(toList());
        return new MultipleItemBrandModel(itemBrandCollected, itemBrandCollected.size());
    }
}
