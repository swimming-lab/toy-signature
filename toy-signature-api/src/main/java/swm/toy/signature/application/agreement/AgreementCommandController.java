package swm.toy.signature.application.agreement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.agreement.AgreementService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

@RestController
public class AgreementCommandController {

    private final AgreementService agreementService;

    AgreementCommandController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements")
    public AgreementModel postAgreement(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody AgreementPostParam param) {
        var agreementCreated = agreementService.createAgreement(jwtPayload.getUserId(), param.toAgreementCreateRequest());
        return AgreementModel.from(agreementCreated);
    }

    @PutMapping("/agreements")
    public AgreementModel putAgreement(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody AgreementPutParam param) {
        final var agreementUpdated = agreementService.updateAgreement(jwtPayload.getUserId(), param.toUpdateRequest());
        return AgreementModel.from(agreementUpdated);
    }

    @PutMapping("/agreements/status")
    public AgreementModel putAgreementStatus(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody AgreementPutParam param) {
        final var agreementUpdated = agreementService.updateAgreementStatus(jwtPayload.getUserId(), param.toUpdateStatusRequest());
        return AgreementModel.from(agreementUpdated);
    }
}
