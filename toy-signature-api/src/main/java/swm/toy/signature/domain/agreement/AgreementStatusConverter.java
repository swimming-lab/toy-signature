package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValueConverter;

class AgreementStatusConverter extends CodeValueConverter<AgreementStatus> {
    AgreementStatusConverter() {
        super(AgreementStatus.class);
    }
}
