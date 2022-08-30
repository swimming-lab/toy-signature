package swm.toy.signature.domain.item;

import lombok.Getter;

@Getter
public class ItemCreateRequest {

    private final ItemContents itemContents;
    private final Long itemTypeId;
    private final Long itemBrandId;

    public ItemCreateRequest(ItemContents itemContents, Long itemTypeId, Long itemBrandId) {
        this.itemContents = itemContents;
        this.itemTypeId = itemTypeId;
        this.itemBrandId = itemBrandId;
    }
}
