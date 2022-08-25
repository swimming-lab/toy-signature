package swm.toy.signature.domain.agreement;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.user.User;

@Table(name = "agreements")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private AgreementContents contents;

    @Convert(converter = AgreementStatusConverter.class)
    private AgreementStatus status = AgreementStatus.PENDING;

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

    public Long getId() {
        return id;
    }

    public AgreementContents getContents() {
        return contents;
    }

    public AgreementStatus getStatus() {
        return status;
    }

    public User getAuthor() {
        return author;
    }

    public AgreementType getAgreementType() {
        return agreementType;
    }

    public Set<AgreementItem> getAgreementItems() {
        return agreementItems;
    }

    public void updateAgreement(AgreementUpdateRequest request) {
        request.getTypeToUpdate().ifPresent(this::changeAgreementType);
        request.getStatusToUpdate().ifPresent(this::changeAgreementStatus);

        contents.updateAgreementContents(request);
    }

    private void changeAgreementType(String typeToUpdate) {
        this.agreementType = AgreementType.valueOf(typeToUpdate);
    }

    private void changeAgreementStatus(String statusToUpdate) {
        this.status = AgreementStatus.valueOf(statusToUpdate);
    }
}
