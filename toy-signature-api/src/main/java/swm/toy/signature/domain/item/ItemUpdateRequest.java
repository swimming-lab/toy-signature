package swm.toy.signature.domain.item;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

public class ItemUpdateRequest {

    private final String licensePlateToUpdate;
    private final Integer sequenceToUpdate;
    private final String insuranceYnToUpdate;
    private final String routineYnToUpdate;
    private final ItemType itemTypeToUpdate;
    private final ItemBrand itemBrandToUpdate;

    public static ItemUpdateRequestBuilder builder() {
        return new ItemUpdateRequestBuilder();
    }

    private ItemUpdateRequest(ItemUpdateRequestBuilder builder) {
        this(
                builder.licensePlateToUpdate,
                builder.sequenceToUpdate,
                builder.insuranceYnToUpdate,
                builder.routineYnToUpdate,
                builder.itemTypeToUpdate,
                builder().itemBrandToUpdate);
    }

    private ItemUpdateRequest(
            String licensePlateToUpdate,
            Integer sequenceToUpdate,
            String insuranceYnToUpdate,
            String routineYnToUpdate,
            ItemType itemTypeToUpdate,
            ItemBrand itemBrandToUpdate) {
        this.licensePlateToUpdate = licensePlateToUpdate;
        this.sequenceToUpdate = sequenceToUpdate;
        this.insuranceYnToUpdate = insuranceYnToUpdate;
        this.routineYnToUpdate = routineYnToUpdate;
        this.itemTypeToUpdate = itemTypeToUpdate;
        this.itemBrandToUpdate = itemBrandToUpdate;
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

    Optional<ItemType> getItemTypeToUpdate() {
        return ofNullable(itemTypeToUpdate);
    }

    Optional<ItemBrand> getItemBrandToUpdate() {
        return ofNullable(itemBrandToUpdate);
    }

    public static class ItemUpdateRequestBuilder {
        private String licensePlateToUpdate;
        private Integer sequenceToUpdate;
        private String insuranceYnToUpdate;
        private String routineYnToUpdate;
        private ItemType itemTypeToUpdate;
        private ItemBrand itemBrandToUpdate;

        public ItemUpdateRequestBuilder licensePlateToUpdate(String licensePlateToUpdate) {
            this.licensePlateToUpdate = licensePlateToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder sequenceToUpdate(Integer sequenceToUpdate) {
            this.sequenceToUpdate = sequenceToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder insuranceYnToUpdate(String insuranceYnToUpdate) {
            this.insuranceYnToUpdate = insuranceYnToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder routineYnToUpdate(String routineYnToUpdate) {
            this.routineYnToUpdate = routineYnToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder itemTypeToUpdate(ItemType itemTypeToUpdate) {
            this.itemTypeToUpdate = itemTypeToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder itemBrandToUpdate(ItemBrand itemBrandToUpdate) {
            this.itemBrandToUpdate = itemBrandToUpdate;
            return this;
        }

        public ItemUpdateRequest build() {
            return new ItemUpdateRequest(this);
        }
    }
}
