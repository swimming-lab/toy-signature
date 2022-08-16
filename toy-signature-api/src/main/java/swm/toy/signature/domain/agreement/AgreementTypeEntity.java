package swm.toy.signature.domain.agreement;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agreement_type")
public class AgreementTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_type_id")
    private Long id;

    @Column(nullable = false)
    private String type;

    @Builder
    public AgreementTypeEntity(String type) {
        this.type = type;
    }
}