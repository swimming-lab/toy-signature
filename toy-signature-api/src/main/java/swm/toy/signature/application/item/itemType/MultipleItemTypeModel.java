package swm.toy.signature.application.item.itemType;

import lombok.Value;
import swm.toy.signature.domain.item.type.ItemType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleItemTypeModel {

    List<ItemTypeModel.ItemTypeModelNested> itemTypes;
    int itemTypeCount;

    public static MultipleItemTypeModel from(List<ItemType> itemTypes) {
        final var itemTypeCollected =
                itemTypes.stream().map(ItemTypeModel.ItemTypeModelNested::from).collect(toList());
        return new MultipleItemTypeModel(itemTypeCollected, itemTypeCollected.size());
    }
}
