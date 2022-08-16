package swm.toy.signature.domain.item;

import javax.persistence.*;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Embeddable
public class ItemContents {

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private Integer sequence;

    @Column(nullable = false)
    private String insuranceYn;

    @Column(nullable = false)
    private String routineYn;

    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemType itemType;

    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemBrand itemBrand;

    public ItemContents(
            String licensePlate, Integer sequence, String insuranceYn, String routineYn) {
        this.licensePlate = licensePlate;
        this.sequence = sequence;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
    }

    protected ItemContents() {}

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getInsuranceYn() {
        return insuranceYn;
    }

    public String getRoutineYn() {
        return routineYn;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ItemBrand getItemBrand() {
        return itemBrand;
    }

    void updateItemContentsIfPresent(ItemUpdateRequest updateRequest) {
        updateRequest
                .getLicensePlateToUpdate()
                .ifPresent(licensePlateToUpdate -> licensePlate = licensePlateToUpdate);
        updateRequest
                .getSequenceToUpdate()
                .ifPresent(sequenceToUpdate -> sequence = sequenceToUpdate);
        updateRequest
                .getInsuranceYnToUpdate()
                .ifPresent(insuranceYnToUpdate -> insuranceYn = insuranceYnToUpdate);
        updateRequest
                .getRoutineYnToUpdate()
                .ifPresent(routineYnToUpdate -> routineYn = routineYnToUpdate);
        updateRequest
                .getItemTypeToUpdate()
                .ifPresent(itemTypeToUpdate -> itemType = itemTypeToUpdate);
        updateRequest
                .getItemBrandToUpdate()
                .ifPresent(itemBrandToUpdate -> itemBrand = itemBrandToUpdate);
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemBrand(ItemBrand itemBrand) {
        this.itemBrand = itemBrand;
    }
}
