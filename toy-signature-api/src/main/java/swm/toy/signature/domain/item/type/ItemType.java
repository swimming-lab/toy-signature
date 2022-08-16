package swm.toy.signature.domain.item.type;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.common.BaseEntity;

@Table(name = "item_type")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ItemType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String heavy;

    static ItemType of(String type, String heavy) {
        return new ItemType(type, heavy);
    }

    private ItemType(String type, String heavy) {
        this.type = type;
        this.heavy = heavy;
    }

    protected ItemType() {}

    void changeType(String type) {
        this.type = type;
    }

    void changeHeavy(String heavy) {
        this.heavy = heavy;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getHeavy() {
        return heavy;
    }
}
