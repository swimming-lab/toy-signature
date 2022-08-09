package swm.toy.signature.domain.agreement.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swm.toy.signature.domain.equip.entity.EquipEntity;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private EquipEntity equip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private AgreementEntity agreement;

    @Builder
    public AgreedEquipEntity(String brand, String heavy, String licensePlate, String insuranceYn, String routineYn, String type, EquipEntity equip, AgreementEntity agreement) {
        this.brand = brand;
        this.heavy = heavy;
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.type = type;
        this.equip = equip;
        this.agreement = agreement;
    }
}
