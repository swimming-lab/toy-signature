package swm.toy.signature.domain.item;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;
import swm.toy.signature.domain.user.User;

import javax.persistence.*;

@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ItemContents contents;

    @Convert(converter = StatusConverter.class)
    private Status status = Status.HIDE;

    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemType itemType;

    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemBrand itemBrand;

    public static Item of(User author, ItemContents contents, ItemType itemType, ItemBrand itemBrand) {
        return new Item(author, contents, itemType, itemBrand);
    }

    private Item(User author, ItemContents contents, ItemType itemType, ItemBrand itemBrand) {
        this.contents = contents;
        this.author = author;
        this.itemType = itemType;
        this.itemBrand = itemBrand;
    }

    protected Item() {}

    public Long getId() {
        return id;
    }

    public ItemContents getContents() {
        return contents;
    }

    public User getAuthor() {
        return author;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ItemBrand getItemBrand() {
        return itemBrand;
    }

    public Status getStatus() {
        return status;
    }

    public void updateItem(ItemUpdateRequest updateRequest) {
        updateItemIfPresent(updateRequest);
        contents.updateItemContentsIfPresent(updateRequest);
    }

    private void updateItemIfPresent(ItemUpdateRequest updateRequest) {
        updateRequest.getItemTypeToUpdate().ifPresent(itemTypeToUpdate -> itemType = itemTypeToUpdate);
        updateRequest.getItemBrandToUpdate().ifPresent(itemBrandToUpdate -> itemBrand = itemBrandToUpdate);
        updateRequest.getStatusToUpdate().ifPresent(this::setStatus);
    }

    public void setStatus(String statusToUpdate) {
        this.status = Status.valueOf(statusToUpdate);
    }
}
