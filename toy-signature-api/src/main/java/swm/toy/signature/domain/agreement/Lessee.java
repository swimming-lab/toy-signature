package swm.toy.signature.domain.agreement;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Lessee {

    @Column(nullable = false)
    private Long lesseeId;

    @Column(nullable = true)
    private String lesseeName;

    @Column(nullable = true)
    private String lesseeTelNo;

    @Column(nullable = true)
    private String lesseeAddr;

    public Long getLesseeId() {
        return lesseeId;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public String getLesseeTelNo() {
        return lesseeTelNo;
    }

    public String getLesseeAddr() {
        return lesseeAddr;
    }
}
