package swm.toy.signature.domain.item;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;
import swm.toy.signature.domain.user.User;

import javax.persistence.*;
import java.util.Optional;

@Getter
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

    public void updateItem(ItemUpdateRequest updateRequest) {
        updateItemIfPresent(updateRequest);
        contents.updateItemContentsIfPresent(updateRequest);
    }

    private void updateItemIfPresent(ItemUpdateRequest updateRequest) {
        Optional.ofNullable(updateRequest.getItemTypeToUpdate()).ifPresent(toUpdate -> this.itemType = toUpdate);
        Optional.ofNullable(updateRequest.getItemBrandToUpdate()).ifPresent(toUpdate -> this.itemBrand = toUpdate);
        Optional.ofNullable(updateRequest.getStatusToUpdate()).ifPresent(toUpdate -> this.status = Status.valueOf(toUpdate));
    }
}
