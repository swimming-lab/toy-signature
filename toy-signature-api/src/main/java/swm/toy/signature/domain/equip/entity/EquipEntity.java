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
    private Integer equipCd;            //'보유장비코드'
    @Column(nullable = false)
    private String equipModel;         //'보유장비모델'
    @Column(nullable = false)
    private String equipNm;            //'장비명'
    @Column(nullable = false)
    private Integer seq;                //'순번'
    @Column(nullable = false)
    private String licensePlate;       //'차량번호'
    @Column(nullable = false)
    private String insuranceYn;        //'보험가입여부'
    @Column(nullable = false)
    private String routineYn;          //'정기검사여부'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity author;

    @Builder
    public EquipEntity(Integer equipCd, String equipModel, String equipNm, Integer seq, String licensePlate, String insuranceYn, String routineYn, UserEntity author) {
        this.equipCd = equipCd;
        this.equipModel = equipModel;
        this.equipNm = equipNm;
        this.seq = seq;
        this.licensePlate = licensePlate;
        this.insuranceYn = insuranceYn;
        this.routineYn = routineYn;
        this.author = author;
    }
}
