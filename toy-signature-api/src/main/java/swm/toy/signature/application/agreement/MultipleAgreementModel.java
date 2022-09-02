package swm.toy.signature.application.agreement;

import lombok.Value;
import swm.toy.signature.domain.agreement.Agreement;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleAgreementModel {

    List<AgreementModel.AgreementModelNested> agreements;
    int itemsCount;

    public static MultipleAgreementModel from(List<Agreement> agreements) {
        final var agreementsCollected = agreements.stream().map(AgreementModel.AgreementModelNested::from)
                .collect(toList());
        return new MultipleAgreementModel(agreementsCollected, agreementsCollected.size());
    }
}
