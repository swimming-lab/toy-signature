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
    private final Long itemTypeIdToUpdate;
    private final Long itemBrandIdToUpdate;

    private ItemType itemTypeToUpdate;
    private ItemBrand itemBrandToUpdate;

    public static ItemUpdateRequestBuilder builder() {
        return new ItemUpdateRequestBuilder();
    }

    private ItemUpdateRequest(ItemUpdateRequestBuilder builder) {
        this(
                builder.licensePlateToUpdate,
                builder.sequenceToUpdate,
                builder.insuranceYnToUpdate,
                builder.routineYnToUpdate,
                builder.itemTypeIdToUpdate,
                builder().itemBrandIdToUpdate);
    }

    private ItemUpdateRequest(
            String licensePlateToUpdate,
            Integer sequenceToUpdate,
            String insuranceYnToUpdate,
            String routineYnToUpdate,
            Long itemTypeIdToUpdate,
            Long itemBrandIdToUpdate) {
        this.licensePlateToUpdate = licensePlateToUpdate;
        this.sequenceToUpdate = sequenceToUpdate;
        this.insuranceYnToUpdate = insuranceYnToUpdate;
        this.routineYnToUpdate = routineYnToUpdate;
        this.itemTypeIdToUpdate = itemTypeIdToUpdate;
        this.itemBrandIdToUpdate = itemBrandIdToUpdate;
    }

    public void setItemTypeToUpdate(ItemType itemTypeToUpdate) {
        this.itemTypeToUpdate = itemTypeToUpdate;
    }

    public void setItemBrandToUpdate(ItemBrand itemBrandToUpdate) {
        this.itemBrandToUpdate = itemBrandToUpdate;
    }

    public Optional<ItemType> getItemTypeToUpdate() {
        return ofNullable(itemTypeToUpdate);
    }

    public Optional<ItemBrand> getItemBrandToUpdate() {
        return ofNullable(itemBrandToUpdate);
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

    public static class ItemUpdateRequestBuilder {
        private String licensePlateToUpdate;
        private Integer sequenceToUpdate;
        private String insuranceYnToUpdate;
        private String routineYnToUpdate;
        private Long itemTypeIdToUpdate;
        private Long itemBrandIdToUpdate;

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

        public ItemUpdateRequestBuilder itemTypeIdToUpdate(Long itemTypeIdToUpdate) {
            this.itemTypeIdToUpdate = itemTypeIdToUpdate;
            return this;
        }

        public ItemUpdateRequestBuilder itemBrandIdToUpdate(Long itemBrandIdToUpdate) {
            this.itemBrandIdToUpdate = itemBrandIdToUpdate;
            return this;
        }

        public ItemUpdateRequest build() {
            return new ItemUpdateRequest(this);
        }
    }
}
