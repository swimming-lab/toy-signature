package swm.toy.signature.domain.equip.entity;

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
@Table(name = "equips")
public class EquipEntity extends BaseEntity {
    @Column(nullable = false)
    private Integer equipCode;
    @Column(nullable = false)
    private String equipModel;
    @Column(nullable = false)
    private String equipName;
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
    public EquipEntity(Integer equipCode, String equipModel, String equipName, Integer seq, String licensePlate, String insuranceYn, String routineYn, UserEntity author) {
        this.equipCode = equipCode;
        this.equipModel = equipModel;
        this.equipName = equipName;
        this.seq = seq;
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.author = author;
    }
}
