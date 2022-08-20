package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValue;

public enum AgreementType implements CodeValue {
    RENT("00", "RENT"),
    ;

    private String code;
    private String value;

    AgreementType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
