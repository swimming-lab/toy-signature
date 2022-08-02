package swm.toy.signature.domain.equip.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swm.toy.signature.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equip_code")
public class EquipCodeEntity extends BaseEntity {
    @Column(nullable = false)
    private Integer equipCd;            //'보유장비코드'
    @Column(nullable = false)
    private String equipModel;         //'보유장비모델'
    @Column(nullable = false)
    private String equipNm;            //'장비명'
}
