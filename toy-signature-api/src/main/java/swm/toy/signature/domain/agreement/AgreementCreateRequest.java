package swm.toy.signature.domain.agreement;

public class AgreementCreateRequest {

    private final AgreementContents agreementContents;

    public AgreementCreateRequest(AgreementContents agreementContents) {
        this.agreementContents = agreementContents;
    }

    public AgreementContents getAgreementContents() {
        return agreementContents;
    }
}
