package swm.toy.signature.domain.agreement;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class AgreementUpdateRequest {

    @NonNull
    private final Long agreementId;

    private final Long lesseeIdToUpdate;
    private final String lesseeNameToUpdate;
    private final String lesseeTelNoToUpdate;
    private final String lesseeAddrToUpdate;
    private final Long amountToUpdate;
    private final Long overAmountToUpdate;
    private final String startDateToUpdate;
    private final String endDateIdToUpdate;
    private final String typeToUpdate;
    private final String statusToUpdate;
    private final String etcToUpdate;

    public Long getAgreementId() {
        return agreementId;
    }

    public Optional<Long> getLesseeIdToUpdate() {
        return ofNullable(lesseeIdToUpdate);
    }

    public Optional<String> getLesseeNameToUpdate() {
        return ofNullable(lesseeNameToUpdate);
    }

    public Optional<String> getLesseeTelNoToUpdate() {
        return ofNullable(lesseeTelNoToUpdate);
    }

    public Optional<String> getLesseeAddrToUpdate() {
        return ofNullable(lesseeAddrToUpdate);
    }

    public Optional<Long> getAmountToUpdate() {
        return ofNullable(amountToUpdate);
    }

    public Optional<Long> getOverAmountToUpdate() {
        return ofNullable(overAmountToUpdate);
    }

    public Optional<String> getStartDateToUpdate() {
        return ofNullable(startDateToUpdate);
    }

    public Optional<String> getEndDateIdToUpdate() {
        return ofNullable(endDateIdToUpdate);
    }

    public Optional<String> getTypeToUpdate() {
        return ofNullable(typeToUpdate);
    }

    public Optional<String> getStatusToUpdate() {
        return ofNullable(statusToUpdate);
    }

    public Optional<String> getEtcToUpdate() {
        return ofNullable(etcToUpdate);
    }
}
