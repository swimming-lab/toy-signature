package swm.toy.signature.domain.agreement.item;

import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.agreement.Agreement;
import swm.toy.signature.domain.item.Item;

import javax.persistence.*;

@Table(name = "agreement_item")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class AgreementItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Agreement agreement;
}
