package swm.toy.signature.domain.equip.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swm.toy.signature.base.entity.BaseEntity;
import swm.toy.signature.domain.user.entity.UserEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equip")
@NamedEntityGraph(name = "fetch-author-equipType-equipBrand", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("equipType"), @NamedAttributeNode("equipBrand")})
public class EquipEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equip_id")
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer sequence;

    @Column(nullable = false)
    private String insuranceYn;

    @Column(nullable = false)
    private String routineYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
//    @Column(name = "user_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
//    @Column(name = "equip_type_id")
    private EquipTypeEntity equipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
//    @Column(name = "equip_brand_id")
    private EquipBrandEntity equipBrand;

    @Builder
    public EquipEntity(String licensePlate, Integer sequence, String insuranceYn, String routineYn, UserEntity author, EquipTypeEntity equipType, EquipBrandEntity equipBrand) {
        this.licensePlate = licensePlate;
        this.sequence = sequence;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.author = author;
        this.equipType = equipType;
        this.equipBrand = equipBrand;
    }
}
