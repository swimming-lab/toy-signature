package swm.toy.signature.domain.equipment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swm.toy.signature.base.entity.BaseEntity;
import swm.toy.signature.domain.user.entity.UserEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class EquipmentEntity extends BaseEntity {
    @Column(nullable = false)
    private Integer seq;
    @Column(nullable = false)
    private String licensePlate;
    @Column(nullable = false)
    private String insuranceYn;
    @Column(nullable = false)
    private String routineYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity author;

    @Builder
    public EquipmentEntity(Integer seq, String licensePlate, String insuranceYn, String routineYn, UserEntity author) {
        this.seq = seq;
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.author = author;
    }
}
