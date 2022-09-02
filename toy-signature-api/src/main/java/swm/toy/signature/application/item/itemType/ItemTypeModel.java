package swm.toy.signature.application.item.itemType;

import lombok.Value;
import swm.toy.signature.domain.item.type.ItemType;

@Value
public class ItemTypeModel {

    ItemTypeModelNested itemType;

    public static ItemTypeModel from(ItemType itemType) {
        return new ItemTypeModel(ItemTypeModelNested.from(itemType));
    }

    @Value
    public static class ItemTypeModelNested {
        Long id;
        String type;
        String heavy;

        public static ItemTypeModelNested from(ItemType itemType) {
            return new ItemTypeModelNested(itemType.getId(), itemType.getType(), itemType.getHeavy());
        }
    }
}
