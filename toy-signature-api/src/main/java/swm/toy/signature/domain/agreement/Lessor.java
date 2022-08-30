package swm.toy.signature.domain.agreement;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
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
}
