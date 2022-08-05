package swm.toy.signature.domain.equip.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equip_brand")
public class EquipBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "equip_brand_id")
    private Long id;

    @Column(nullable = false)
    private String brandName;

    @Builder
    public EquipBrandEntity(String brandName) {
        this.brandName = brandName;
    }
}
