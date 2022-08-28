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

    public static Lessor of(Long lessorId, String lessorName, String lessorTelNo, String lessorAddr) {
        return new Lessor(lessorId, lessorName, lessorTelNo, lessorAddr);
    }

    private Lessor(Long lessorId, String lessorName, String lessorTelNo, String lessorAddr) {
        this.lessorId = lessorId;
        this.lessorName = lessorName;
        this.lessorTelNo = lessorTelNo;
        this.lessorAddr = lessorAddr;
    }

    protected Lessor() {}

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
