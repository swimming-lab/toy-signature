package swm.toy.signature.domain.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemContents {

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private Integer sequence;

    @Column(nullable = false)
    private String insuranceYn;

    @Column(nullable = false)
    private String routineYn;

    @Column(length = 500)
    private String etc;

    public static ItemContents of(
            String licensePlate, Integer sequence, String insuranceYn, String routineYn, String etc) {
        return new ItemContents(licensePlate, sequence, insuranceYn, routineYn, etc);
    }

    private ItemContents(String licensePlate, Integer sequence, String insuranceYn, String routineYn, String etc) {
        this.licensePlate = licensePlate;
        this.sequence = sequence;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.etc = etc;
    }

    protected ItemContents() {}

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getInsuranceYn() {
        return insuranceYn;
    }

    public String getRoutineYn() {
        return routineYn;
    }

    public String getEtc() {
        return etc;
    }

    void updateItemContentsIfPresent(ItemUpdateRequest request) {
        request.getLicensePlateToUpdate().ifPresent(licensePlateToUpdate -> licensePlate = licensePlateToUpdate);
        request.getSequenceToUpdate().ifPresent(sequenceToUpdate -> sequence = sequenceToUpdate);
        request.getInsuranceYnToUpdate().ifPresent(insuranceYnToUpdate -> insuranceYn = insuranceYnToUpdate);
        request.getRoutineYnToUpdate().ifPresent(routineYnToUpdate -> routineYn = routineYnToUpdate);
        request.getEtcToUpdate().ifPresent(etcToUpdate -> etc = etcToUpdate);
    }
}
