package swm.toy.signature.application.item;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.Value;
import org.springframework.data.domain.Page;
import swm.toy.signature.application.item.ItemModel.ItemModelNested;
import swm.toy.signature.domain.item.Item;

@Value
class MultipleItemModel {

    List<ItemModelNested> items;
    int itemsCount;

    static MultipleItemModel fromItems(Page<Item> items) {
        final var itemsCollected = items.map(ItemModelNested::fromItem).stream().collect(toList());
        return new MultipleItemModel(itemsCollected, itemsCollected.size());
    }
}
