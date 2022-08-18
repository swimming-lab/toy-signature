package swm.toy.signature.application.item.itemType;

import lombok.Value;
import swm.toy.signature.domain.item.type.ItemType;

@Value
public class ItemTypeModel {

    ItemTypeModelNested itemType;

    static ItemTypeModel fromItemType(ItemType itemType) {
        return new ItemTypeModel(ItemTypeModelNested.fromItemType(itemType));
    }

    @Value
    static class ItemTypeModelNested {
        Long id;
        String type;
        String heavy;

        static ItemTypeModelNested fromItemType(ItemType itemType) {
            return new ItemTypeModelNested(
                    itemType.getId(),
                    itemType.getType(),
                    itemType.getHeavy());
        }
    }
}
