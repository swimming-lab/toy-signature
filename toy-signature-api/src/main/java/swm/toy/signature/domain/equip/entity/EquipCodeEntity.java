package swm.toy.signature.domain.equip.entity;

import lombok.Builder;
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
    private String equipCode;
    @Column(nullable = false)
    private String equipModel;
    @Column(nullable = false)
    private String equipName;

    @Builder
    public EquipCodeEntity(String equipCode, String equipModel, String equipName) {
        this.equipCode = equipCode;
        this.equipModel = equipModel;
        this.equipName = equipName;
    }
}
