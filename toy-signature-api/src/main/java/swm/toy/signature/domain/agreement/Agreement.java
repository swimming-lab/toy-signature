package swm.toy.signature.domain.agreement;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.user.User;
import swm.toy.signature.infrastructure.exception.AppException;
import swm.toy.signature.infrastructure.exception.ErrorCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Table(name = "agreements")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AgreementContents contents;

    @Column(nullable = false, length = 20)
    //    @Enumerated(value = EnumType.STRING)
    @Convert(converter = AgreementStatusConverter.class)
    private AgreementStatus status = AgreementStatus.PENDING;

    @Column(nullable = false, length = 20)
    //    @Enumerated(value = EnumType.STRING)
    @Convert(converter = AgreementTypeConverter.class)
    private AgreementType agreementType = AgreementType.RENT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User author;

    @OneToMany(mappedBy = "agreement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AgreementItem> agreementItems = new HashSet<>();

    public static Agreement of(User author, AgreementContents contents) {
        return new Agreement(author, contents);
    }

    private Agreement(User author, AgreementContents contents) {
        this.author = author;
        this.contents = contents;
    }

    protected Agreement() {}

    public void addAgreementItems(AgreementItem agreementItem) {
        this.agreementItems.add(agreementItem);
        agreementItem.setAgreement(this);
    }

    public Set<AgreementItem> getAgreementItems() {
        return agreementItems;
    }

    public void updateAgreement(AgreementUpdateRequest request) {
        checkUpdateStatus();

        Optional.ofNullable(request.getTypeToUpdate()).ifPresent(toUpdate -> this.agreementType = AgreementType.valueOf(toUpdate));

        contents.updateAgreementContents(request);
    }

    public Agreement updateStatus(AgreementUpdateRequest request) {
        Optional.ofNullable(request.getStatusToUpdate()).ifPresent(toUpdate -> this.status = AgreementStatus.valueOf(toUpdate));

        return this;
    }

    private void checkUpdateStatus() {
        if (this.status != AgreementStatus.PENDING) {
            throw new AppException(ErrorCode.AGREEMENT_CAN_NOT_CHANGE_STATUS);
        }
    }
}
