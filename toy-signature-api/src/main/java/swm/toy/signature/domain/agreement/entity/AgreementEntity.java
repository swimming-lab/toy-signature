package swm.toy.signature.domain.agreement.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swm.toy.signature.base.entity.BaseEntity;
import swm.toy.signature.domain.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agreement")
@NamedEntityGraph(name = "fetch-author-agreementType-agreedEquip", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("agreementType"), @NamedAttributeNode("agreedEquips")})
public class AgreementEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Long lessorId;

    @Column(nullable = false)
    private String lessorName;

    @Column(nullable = false)
    private String lessorTelNo;

    @Column(nullable = false)
    private String lessorAddr;

    @Column(nullable = false)
    private Long lesseeId;

    @Column(nullable = false)
    private String lesseeName;

    @Column(nullable = false)
    private String lesseeTelNo;

    @Column(nullable = false)
    private String lesseeAddr;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long overAmount;

    @Column(nullable = false)
    private String etc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private AgreementTypeEntity agreementType;

    @OneToMany(mappedBy = "agreement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AgreedEquipEntity> agreedEquips;

    @Builder
    public AgreementEntity(LocalDateTime startDate, LocalDateTime endDate, Long lessorId, String lessorName, String lessorTelNo, String lessorAddr, Long lesseeId, String lesseeName, String lesseeTelNo, String lesseeAddr, String status, Long amount, Long overAmount, String etc, UserEntity author, AgreementTypeEntity agreementType, List<AgreedEquipEntity> agreedEquips) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.lessorId = lessorId;
        this.lessorName = lessorName;
        this.lessorTelNo = lessorTelNo;
        this.lessorAddr = lessorAddr;
        this.lesseeId = lesseeId;
        this.lesseeName = lesseeName;
        this.lesseeTelNo = lesseeTelNo;
        this.lesseeAddr = lesseeAddr;
        this.status = status;
        this.amount = amount;
        this.overAmount = overAmount;
        this.etc = etc;
        this.author = author;
        this.agreementType = agreementType;
        this.agreedEquips = agreedEquips;
    }
}
