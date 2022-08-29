package swm.toy.signature.application.agreement;

import lombok.Value;
import org.springframework.data.domain.Page;
import swm.toy.signature.domain.agreement.Agreement;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
class MultipleAgreementModel {

    List<AgreementModel.AgreementModelNested> agreements;
    int itemsCount;

    static MultipleAgreementModel from(Page<Agreement> agreements) {
        final var agreementsCollected = agreements.map(AgreementModel.AgreementModelNested::from).stream()
                .collect(toList());
        return new MultipleAgreementModel(agreementsCollected, agreementsCollected.size());
    }
}
