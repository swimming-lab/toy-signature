package swm.toy.signature.domain.agreement;

import swm.toy.signature.infrastructure.converter.CodeValue;

public enum AgreementStatus implements CodeValue {
    EXPIRED("00", "EXPIRED"),       // 계약만료
    ING("01", "ING"),               // 계약완료(계약중인 상태)
    PENDING("02", "PENDING"),       // 계약대기
    CANCELED("03", "CANCELED"),     // 계약취소(대기 > 취소)
    DESTROYED("04", "DESTROYED"),   // 계약파기(중도취소)
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
