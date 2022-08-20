package swm.toy.signature.domain.agreement;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Lessor {

    @Column(nullable = false)
    private Long lessorId;

    @Column(nullable = false)
    private String lessorName;

    @Column(nullable = false)
    private String lessorTelNo;

    @Column(nullable = false)
    private String lessorAddr;

    public Long getLessorId() {
        return lessorId;
    }

    public String getLessorName() {
        return lessorName;
    }

    public String getLessorTelNo() {
        return lessorTelNo;
    }

    public String getLessorAddr() {
        return lessorAddr;
    }
}
