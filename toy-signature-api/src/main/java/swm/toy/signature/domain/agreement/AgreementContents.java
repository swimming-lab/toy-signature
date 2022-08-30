package swm.toy.signature.domain.agreement;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
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

    public static AgreementContents of(
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

    protected void updateAgreementContents(AgreementUpdateRequest request) {
        Optional.ofNullable(request.getStartDateToUpdate()).ifPresent(toUpdate -> this.startDate = LocalDateTime.parse(toUpdate, getLocalDataTimeFormatter()));
        Optional.ofNullable(request.getEndDateIdToUpdate()).ifPresent(toUpdate -> this.endDate = LocalDateTime.parse(toUpdate, getLocalDataTimeFormatter()));
        Optional.ofNullable(request.getAmountToUpdate()).ifPresent(toUpdate -> this.amount = toUpdate);
        Optional.ofNullable(request.getOverAmountToUpdate()).ifPresent(toUpdate -> this.overAmount = toUpdate);
        Optional.ofNullable(request.getEtcToUpdate()).ifPresent(toUpdate -> this.etc = toUpdate);

        lessee.updateLessee(request);
    }

    private DateTimeFormatter getLocalDataTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
