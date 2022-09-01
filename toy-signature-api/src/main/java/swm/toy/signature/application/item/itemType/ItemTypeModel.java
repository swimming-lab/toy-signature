package swm.toy.signature.application.item.itemType;

import lombok.Value;
import swm.toy.signature.domain.item.type.ItemType;

@Value
public class ItemTypeModel {

    Long id;
    String type;
    String heavy;

    public static ItemTypeModel from(ItemType itemType) {
        return new ItemTypeModel(itemType.getId(), itemType.getType(), itemType.getHeavy());
    }
}
