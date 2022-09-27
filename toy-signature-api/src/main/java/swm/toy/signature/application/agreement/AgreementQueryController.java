package swm.toy.signature.application.agreement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.signature.application.common.ResponseModel;
import swm.toy.signature.domain.agreement.AgreementService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RestController
public class AgreementQueryController {

    private final AgreementService agreementService;

    AgreementQueryController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @GetMapping(value = "/agreements")
    public MultipleAgreementModel getAgreemetns(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var agreements = agreementService.getAgreementByAuthorId(jwtPayload.getUserId(), pageable);
        return MultipleAgreementModel.from(agreements);
    }

    @GetMapping(
            value = "/agreements",
            params = {"author"})
    public MultipleAgreementModel getAgreementsByAuthor(
            @RequestParam String authorId,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var agreements = agreementService.getAgreementByAuthorId(Long.valueOf(authorId), pageable);
        return MultipleAgreementModel.from(agreements);
    }

    @GetMapping(value = "/agreements/items")
    public MultipleAgreementModel getAgreementsItems(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var agreements = agreementService.getAgreementItems(jwtPayload.getUserId(), pageable);
        return MultipleAgreementModel.from(agreements);
    }

    @GetMapping(value = "/agreements/count")
    public ResponseEntity getAgreementsCountByAuthor(
            @AuthenticationPrincipal UserJWTPayload jwtPayload) {
        final var count = agreementService.getAgreementCountByAuthorId(jwtPayload.getUserId());
        return ResponseEntity.ok(ResponseModel.response("count", count));
    }
}
