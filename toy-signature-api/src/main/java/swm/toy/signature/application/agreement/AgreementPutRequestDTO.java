package swm.toy.signature.application.agreement;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.agreement.AgreementUpdateRequest;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("item")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class AgreementPutRequestDTO {

    @NotNull
    private Long id;

    private Long lesseeId;
    private String lesseeName;
    private String lesseeAddr;
    private String lesseeTelNo;
    private String startDate;
    private String endDate;
    private Long amount;
    private Long overAmount;
    private String etc;
    private String status;

    AgreementUpdateRequest toUpdateRequest() {
        return AgreementUpdateRequest.builder()
                .agreementId(id)
                .lesseeIdToUpdate(ofNullable(lesseeId).orElse(null))
                .lesseeNameToUpdate(ofNullable(lesseeName).orElse(null))
                .lesseeTelNoToUpdate(ofNullable(lesseeTelNo).orElse(null))
                .lesseeAddrToUpdate(ofNullable(lesseeAddr).orElse(null))
                .startDateToUpdate(ofNullable(startDate).orElse(null))
                .endDateIdToUpdate(ofNullable(endDate).orElse(null))
                .amountToUpdate(ofNullable(amount).orElse(null))
                .overAmountToUpdate(ofNullable(overAmount).orElse(null))
                .etcToUpdate(ofNullable(etc).orElse(null))
                .statusToUpdate(ofNullable(status).orElse(null))
                .build();
    }
}
