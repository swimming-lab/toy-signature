package swm.toy.signature.domain.item;

public class ItemCreateRequest {

    private final ItemContents itemContents;
    private final Long itemTypeId;
    private final Long itemBrandId;

    public ItemCreateRequest(ItemContents itemContents, Long itemTypeId, Long itemBrandId) {
        this.itemContents = itemContents;
        this.itemTypeId = itemTypeId;
        this.itemBrandId = itemBrandId;
    }

    public ItemContents getItemContents() {
        return itemContents;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public Long getItemBrandId() {
        return itemBrandId;
    }
}
