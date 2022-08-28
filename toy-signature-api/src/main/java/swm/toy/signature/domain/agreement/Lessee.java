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

    protected void updateLessee(AgreementUpdateRequest request) {
        request.getLesseeIdToUpdate().ifPresent(this::changeLesseeId);
        request.getLesseeNameToUpdate().ifPresent(this::changeLesseeName);
        request.getLesseeTelNoToUpdate().ifPresent(this::changeLesseeTelNo);
        request.getLesseeAddrToUpdate().ifPresent(this::changeLesseeAddr);
    }

    private void changeLesseeId(Long lesseeIdToUpdate) {
        this.lesseeId = lesseeIdToUpdate;
    }

    private void changeLesseeName(String lesseeNameToUpdate) {
        this.lesseeName = lesseeNameToUpdate;
    }

    private void changeLesseeTelNo(String lesseeNameToUpdate) {
        this.lesseeName = lesseeNameToUpdate;
    }

    private void changeLesseeAddr(String lesseeNameToUpdate) {
        this.lesseeName = lesseeNameToUpdate;
    }
}
