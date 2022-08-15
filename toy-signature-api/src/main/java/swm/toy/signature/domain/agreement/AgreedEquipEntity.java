package swm.toy.signature.domain.agreement;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import swm.toy.signature.domain.item.Item;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agreed_equip")
public class AgreedEquipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreed_equip_id")
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String heavy;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String insuranceYn;

    @Column(nullable = false)
    private String routineYn;

    @Column(nullable = false)
    private String type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(nullable = false)
    private AgreementEntity agreement;

    @Builder
    public AgreedEquipEntity(
            String brand,
            String heavy,
            String licensePlate,
            String insuranceYn,
            String routineYn,
            String type,
            Item item,
            AgreementEntity agreement) {
        this.brand = brand;
        this.heavy = heavy;
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.type = type;
        this.item = item;
        this.agreement = agreement;
    }
}
