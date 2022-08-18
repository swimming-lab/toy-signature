package swm.toy.signature.application.item.itemType;

import lombok.Value;
import swm.toy.signature.application.item.itemType.ItemTypeModel.ItemTypeModelNested;
import swm.toy.signature.domain.item.type.ItemType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleItemTypeModel {

    List<ItemTypeModelNested> itemTypes;
    int itemTypeCount;

    static MultipleItemTypeModel fromItemTypes(List<ItemType> itemTypes) {
        final var itemTypeCollected = itemTypes.stream().map(ItemTypeModelNested::fromItemType).collect(toList());
        return new MultipleItemTypeModel(itemTypeCollected, itemTypeCollected.size());
    }
}
