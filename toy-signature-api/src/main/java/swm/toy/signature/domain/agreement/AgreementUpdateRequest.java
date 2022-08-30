package swm.toy.signature.domain.agreement;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
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
}
