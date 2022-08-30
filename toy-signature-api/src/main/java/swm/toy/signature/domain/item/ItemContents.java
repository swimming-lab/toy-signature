package swm.toy.signature.domain.item;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;

@Getter
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

    void updateItemContentsIfPresent(ItemUpdateRequest request) {
        Optional.ofNullable(request.getLicensePlateToUpdate()).ifPresent(toUpdate -> this.licensePlate = toUpdate);
        Optional.ofNullable(request.getSequenceToUpdate()).ifPresent(toUpdate -> this.sequence = toUpdate);
        Optional.ofNullable(request.getInsuranceYnToUpdate()).ifPresent(toUpdate -> this.insuranceYn = toUpdate);
        Optional.ofNullable(request.getRoutineYnToUpdate()).ifPresent(toUpdate -> this.routineYn = toUpdate);
        Optional.ofNullable(request.getEtcToUpdate()).ifPresent(toUpdate -> this.etc = toUpdate);
    }
}
