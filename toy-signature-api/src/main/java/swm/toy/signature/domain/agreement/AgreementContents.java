package swm.toy.signature.domain.agreement;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class AgreementContents {

    @Embedded private Lessor lessor;

    @Embedded private Lessee lessee;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long overAmount;

    @Column(length = 500)
    private String etc;

    static AgreementContents of(
            Lessor lessor,
            Lessee lessee,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long amount,
            Long overAmount,
            String etc) {
        return new AgreementContents(lessor, lessee, startDate, endDate, amount, overAmount, etc);
    }

    private AgreementContents(
            Lessor lessor,
            Lessee lessee,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long amount,
            Long overAmount,
            String etc) {
        this.lessor = lessor;
        this.lessee = lessee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.overAmount = overAmount;
        this.etc = etc;
    }

    protected AgreementContents() {}

    public Lessor getLessor() {
        return lessor;
    }

    public Lessee getLessee() {
        return lessee;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getOverAmount() {
        return overAmount;
    }

    public String getEtc() {
        return etc;
    }
}
