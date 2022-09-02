package swm.toy.signature.application.item;

import lombok.Value;
import swm.toy.signature.domain.item.Item;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleItemModel {

    List<ItemModel.ItemModelNested> items;
    int itemsCount;

    public static MultipleItemModel from(List<Item> items) {
        final var itemsCollected = items.stream().map(ItemModel.ItemModelNested::from).collect(toList());
        return new MultipleItemModel(itemsCollected, itemsCollected.size());
    }
}
