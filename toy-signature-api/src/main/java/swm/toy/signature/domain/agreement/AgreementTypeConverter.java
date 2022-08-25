package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValueConverter;

class AgreementTypeConverter extends CodeValueConverter<AgreementType> {
    AgreementTypeConverter() {
        super(AgreementType.class);
    }
}
