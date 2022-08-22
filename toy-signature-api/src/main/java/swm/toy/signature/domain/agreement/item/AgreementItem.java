package swm.toy.signature.domain.agreement.item;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.agreement.Agreement;
import swm.toy.signature.domain.item.Item;
import swm.toy.signature.domain.item.ItemContents;

@Table(name = "agreement_item")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class AgreementItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String insuranceYn;

    @Column(nullable = false)
    private String routineYn;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String heavy;

    @Column(nullable = false)
    private String brand;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(nullable = false)
    private Agreement agreement;

    public static AgreementItem of(Item item) {
        ItemContents contents = item.getContents();
        return new AgreementItem(
                contents.getLicensePlate(),
                contents.getInsuranceYn(),
                contents.getRoutineYn(),
                item.getItemType().getType(),
                item.getItemType().getHeavy(),
                item.getItemBrand().getBrandName(),
                item);
    }

    private AgreementItem(
            String licensePlate,
            String insuranceYn,
            String routineYn,
            String type,
            String heavy,
            String brand,
            Item item) {
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.type = type;
        this.heavy = heavy;
        this.brand = brand;
        this.item = item;
        //        this.agreement = agreement;
    }

    protected AgreementItem() {}

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
}
