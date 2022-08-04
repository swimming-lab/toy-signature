package swm.toy.signature.domain.equipment.entity;

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
@Table(name = "equipment_cate")
public class EquipmentCateEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Builder
    public EquipmentCateEntity(String name) {
        this.name = name;
    }
}
