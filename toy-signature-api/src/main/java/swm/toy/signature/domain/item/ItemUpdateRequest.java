package swm.toy.signature.domain.item;

import lombok.Builder;
import lombok.NonNull;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

import java.util.Optional;

import static java.util.Optional.ofNullable;

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

    public Long getItemId() {
        return itemId;
    }

    Optional<String> getLicensePlateToUpdate() {
        return ofNullable(licensePlateToUpdate);
    }

    Optional<Integer> getSequenceToUpdate() {
        return ofNullable(sequenceToUpdate);
    }

    Optional<String> getInsuranceYnToUpdate() {
        return ofNullable(insuranceYnToUpdate);
    }

    Optional<String> getRoutineYnToUpdate() {
        return ofNullable(routineYnToUpdate);
    }

    Optional<Long> getItemTypeIdToUpdate() {
        return ofNullable(itemTypeIdToUpdate);
    }

    Optional<Long> getItemBrandIdToUpdate() {
        return ofNullable(itemBrandIdToUpdate);
    }

    Optional<ItemType> getItemTypeToUpdate() {
        return ofNullable(itemTypeToUpdate);
    }

    Optional<ItemBrand> getItemBrandToUpdate() {
        return ofNullable(itemBrandToUpdate);
    }

    Optional<String> getEtcToUpdate() {
        return ofNullable(etcToUpdate);
    }

    Optional<String> getStatusToUpdate() {
        return ofNullable(statusToUpdate);
    }

    void setItemTypeToUpdate(ItemType itemTypeToUpdate) {
        this.itemTypeToUpdate = itemTypeToUpdate;
    }

    void setItemBrandToUpdate(ItemBrand itemBrandToUpdate) {
        this.itemBrandToUpdate = itemBrandToUpdate;
    }
}
