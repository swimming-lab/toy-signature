package swm.toy.signature.domain.agreement;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.common.BaseEntity;
import swm.toy.signature.domain.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "agreements")
@EntityListeners(AuditingEntityListener.class)
@Entity
//@NamedEntityGraph(
//        name = "fetch-author-agreementType-agreedEquip",
//        attributeNodes = {
//            @NamedAttributeNode("author"),
//            @NamedAttributeNode("agreementType"),
//            @NamedAttributeNode("agreedEquips")
//        })
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AgreementContents contents;

    @Convert(converter = AgreementStatusConverter.class)
    private AgreementStatus status = AgreementStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User author;

    @Convert(converter = AgreementStatusConverter.class)
    private AgreementType agreementType = AgreementType.RENT;

    @OneToMany(mappedBy = "agreement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AgreementItem> agreementItems = new HashSet<>();

    public Agreement(User author, AgreementContents contents) {
        this.author = author;
        this.contents = contents;
    }

    protected Agreement() {
    }

    public void addAgreedEquips(AgreementItem agreementItem) {
        this.agreementItems.add(agreementItem);
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
}
