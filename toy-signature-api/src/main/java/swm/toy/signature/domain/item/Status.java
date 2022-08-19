package swm.toy.signature.domain.item;

import swm.toy.signature.infrastructure.converter.CodeValue;

public enum Status implements CodeValue {
    HIDE("00", "HIDE"),
    PUBLISH("01", "PUBLISH"),
    RENT_OUT("02", "RENT_OUT"),
    ;

    private String code;
    private String value;

    Status(String code, String value) {
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
