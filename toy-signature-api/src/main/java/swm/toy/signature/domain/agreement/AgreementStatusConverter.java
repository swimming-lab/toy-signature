package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValueConverter;

public class AgreementStatusConverter extends CodeValueConverter<AgreementStatus> {
    AgreementStatusConverter() {
        super(AgreementStatus.class);
    }
}
