package swm.toy.signature.domain.item;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.user.User;

@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
@Entity
// @NamedEntityGraph(name = "fetch-author-equipType-equipBrand", attributeNodes =
// {@NamedAttributeNode("author"), @NamedAttributeNode("equipType"),
// @NamedAttributeNode("equipBrand")})
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private ItemContents contents;

    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    public Item(User author, ItemContents contents) {
        this.author = author;
        this.contents = contents;
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

    public void updateItem(ItemUpdateRequest updateRequest) {
        contents.updateItemContentsIfPresent(updateRequest);
    }
}
