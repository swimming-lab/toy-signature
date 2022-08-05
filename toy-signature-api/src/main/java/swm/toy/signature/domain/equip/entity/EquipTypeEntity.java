package swm.toy.signature.domain.equip.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equip_type")
public class EquipTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equip_type_id")
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String heavy;

    @Builder
    public EquipTypeEntity(String type, String heavy) {
        this.type = type;
        this.heavy = heavy;
    }
}
