package swm.toy.signature.domain.agreement;

import java.time.LocalDateTime;
import java.util.Set;

public class AgreementCreateRequest {

    private final AgreementContents agreementContents;
    private final Set<Long> itemIds;

    public static AgreementCreateRequest of(
            Long lessorId,
            String lessorName,
            String lessorAddr,
            String lessorTelNo,
            Long lesseeId,
            String lesseeName,
            String lesseeAddr,
            String lesseeTelNo,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long amount,
            Long overAmount,
            String etc,
            Set<Long> itemIds) {
        return new AgreementCreateRequest(
                lessorId,
                lessorName,
                lessorAddr,
                lessorTelNo,
                lesseeId,
                lesseeName,
                lesseeAddr,
                lesseeTelNo,
                startDate,
                endDate,
                amount,
                overAmount,
                etc,
                itemIds);
    }

    private AgreementCreateRequest(
            Long lessorId,
            String lessorName,
            String lessorAddr,
            String lessorTelNo,
            Long lesseeId,
            String lesseeName,
            String lesseeAddr,
            String lesseeTelNo,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long amount,
            Long overAmount,
            String etc,
            Set<Long> itemIds) {
        this.agreementContents = AgreementContents.of(
                Lessor.of(lessorId, lessorName, lessorTelNo, lessorAddr),
                Lessee.of(lesseeId, lesseeName, lesseeTelNo, lesseeAddr),
                startDate,
                endDate,
                amount,
                overAmount,
                etc);
        this.itemIds = itemIds;
    }

    public AgreementContents getAgreementContents() {
        return agreementContents;
    }

    public Set<Long> getItemIds() {
        return itemIds;
    }
}
