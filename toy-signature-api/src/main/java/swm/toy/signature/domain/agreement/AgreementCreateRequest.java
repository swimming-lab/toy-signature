package swm.toy.signature.domain.agreement;

import java.util.Set;

public class AgreementCreateRequest {

    private final AgreementContents agreementContents;
    private final Set<Long> itemIds;

    public AgreementCreateRequest(AgreementContents agreementContents, Set<Long> itemIds) {
        this.agreementContents = agreementContents;
        this.itemIds = itemIds;
    }

    public AgreementContents getAgreementContents() {
        return agreementContents;
    }

    public Set<Long> getItemIds() {
        return itemIds;
    }
}
