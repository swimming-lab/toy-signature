package swm.toy.signature.domain.user;

import swm.toy.signature.infrastructure.converter.CodeValue;

public enum UserStatus implements CodeValue {
    USED("00", "USED"),
    UNUSED("01", "UNUSED"),
    REMOVE("02", "REMOVE"),
    ;

    private String code;
    private String value;

    UserStatus(String code, String value) {
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
