package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValue;

public enum AgreementStatus implements CodeValue {
    EXPIRED("00", "EXPIRED"),
    ING("01", "ING"),
    PENDING("02", "PENDING"),
    CANCELED("03", "CANCELED"),
    DESTROYED("04", "DESTROYED"),
    ;

    private String code;
    private String value;

    AgreementStatus(String code, String value) {
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
