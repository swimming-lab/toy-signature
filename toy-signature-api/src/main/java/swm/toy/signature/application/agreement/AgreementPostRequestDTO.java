package swm.toy.signature.application.agreement;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.agreement.AgreementCreateRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("agreement")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class AgreementPostRequestDTO {

    @NotNull
    private Long lessorId;

    @NotNull
    private String lessorName;

    @NotNull
    private String lessorAddr;

    @NotNull
    private String lessorTelNo;

    @NotNull
    private Long lesseeId;

    @NotNull
    private String lesseeName;

    @NotNull
    private String lesseeAddr;

    @NotNull
    private String lesseeTelNo;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Long amount;

    @NotNull
    private Long overAmount;

    private String etc;

    @NotNull
    private Set<Long> itemIds;

    AgreementCreateRequest toAgreementCreateRequest() {
        return AgreementCreateRequest.of(
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
}
