package swm.toy.signature.domain.agreement;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;

@Getter
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

    public static Lessee of(Long lesseeId, String lesseeName, String lesseeTelNo, String lesseeAddr) {
        return new Lessee(lesseeId, lesseeName, lesseeTelNo, lesseeAddr);
    }

    private Lessee(Long lesseeId, String lesseeName, String lesseeTelNo, String lesseeAddr) {
        this.lesseeId = lesseeId;
        this.lesseeName = lesseeName;
        this.lesseeTelNo = lesseeTelNo;
        this.lesseeAddr = lesseeAddr;
    }

    protected Lessee() {}

    protected void updateLessee(AgreementUpdateRequest request) {
        Optional.ofNullable(request.getLesseeIdToUpdate()).ifPresent(toUpdate -> this.lesseeId = toUpdate);
        Optional.ofNullable(request.getLesseeNameToUpdate()).ifPresent(toUpdate -> this.lesseeName = toUpdate);
        Optional.ofNullable(request.getLesseeTelNoToUpdate()).ifPresent(toUpdate -> this.lesseeTelNo = toUpdate);
        Optional.ofNullable(request.getLesseeAddrToUpdate()).ifPresent(toUpdate -> this.lesseeAddr = toUpdate);
    }
}
