package swm.toy.signature.application.agreement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.agreement.AgreementService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

import static swm.toy.signature.application.common.ResponseModel.response;

@RestController
public class AgreementRestController {

    private final AgreementService agreementService;

    AgreementRestController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("/agreements")
    public ResponseEntity postAgreement(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody AgreementPostParam param) {
        var agreementCreated = agreementService.createAgreement(jwtPayload.getUserId(), param.toAgreementCreateRequest());
        return ResponseEntity.ok(response("agreement", agreementCreated));
    }

    @GetMapping(value = "/agreements")
    public ResponseEntity getAgreemetns(
            @AuthenticationPrincipal UserJWTPayload jwtPayload,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var agreements = agreementService.getAgreementByAuthorId(jwtPayload.getUserId(), pageable);
        return ResponseEntity.ok(response("agreements", agreements));
    }

    @GetMapping(
            value = "/agreements",
            params = {"author"})
    public ResponseEntity getItemsByAuthor(
            @RequestParam String authorId,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var agreements = agreementService.getAgreementByAuthorId(Long.valueOf(authorId), pageable);
        return ResponseEntity.ok(response("agreements", agreements));
    }

    @PutMapping("/agreements")
    public ResponseEntity putItem(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @RequestBody AgreementPutParam param) {
        final var agreementUpdated = agreementService.updateAgreement(jwtPayload.getUserId(), param.toUpdateRequest());
        return ResponseEntity.ok(response("agreement", agreementUpdated));
    }
}
