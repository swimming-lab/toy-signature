package swm.toy.signature.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Getter
@Builder
public class ItemUpdateRequest {
    @NonNull
    private final Long itemId;

    private final String licensePlateToUpdate;
    private final Integer sequenceToUpdate;
    private final String insuranceYnToUpdate;
    private final String routineYnToUpdate;
    private final Long itemTypeIdToUpdate;
    private final Long itemBrandIdToUpdate;
    private final String etcToUpdate;
    private final String statusToUpdate;
    private ItemType itemTypeToUpdate;
    private ItemBrand itemBrandToUpdate;

    void setItemTypeToUpdate(ItemType itemTypeToUpdate) {
        this.itemTypeToUpdate = itemTypeToUpdate;
    }

    void setItemBrandToUpdate(ItemBrand itemBrandToUpdate) {
        this.itemBrandToUpdate = itemBrandToUpdate;
    }
}
