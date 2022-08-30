package swm.toy.signature.domain.item.brand;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.common.BaseEntity;

import javax.persistence.*;

@Getter
@Table(name = "item_brand")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ItemBrand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String brandName;

    static ItemBrand from(String brandName) {
        return new ItemBrand(brandName);
    }

    private ItemBrand(String brandName) {
        this.brandName = brandName;
    }

    protected ItemBrand() {}

    void changeBrandName(String brandName) {
        this.brandName = brandName;
    }
}