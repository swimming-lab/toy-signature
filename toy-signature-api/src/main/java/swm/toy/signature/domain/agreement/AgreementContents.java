package swm.toy.signature.domain.agreement;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class AgreementContents {

    @Embedded
    private Lessor lessor;

    @Embedded
    private Lessee lessee;

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

    protected void updateAgreementContents(AgreementUpdateRequest request) {
        request.getStartDateToUpdate().ifPresent(this::changeStartDate);
        request.getEndDateIdToUpdate().ifPresent(this::changeEndDate);
        request.getAmountToUpdate().ifPresent(this::changeAmount);
        request.getOverAmountToUpdate().ifPresent(this::changeOverAmount);
        request.getEtcToUpdate().ifPresent(this::changeEtc);

        lessee.updateLessee(request);
    }

    private void changeStartDate(String startDateToUpdate) {
        this.startDate = LocalDateTime.parse(startDateToUpdate, getLocalDataTimeFormatter());
    }

    private void changeEndDate(String endDateToUpdate) {
        this.endDate = LocalDateTime.parse(endDateToUpdate, getLocalDataTimeFormatter());
    }

    private void changeAmount(Long amountToUpdate) {
        this.amount = amountToUpdate;
    }

    private void changeOverAmount(Long overAmountToUpdate) {
        this.overAmount = overAmountToUpdate;
    }

    private void changeEtc(String etcToUpdate) {
        this.etc = etcToUpdate;
    }

    private DateTimeFormatter getLocalDataTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
