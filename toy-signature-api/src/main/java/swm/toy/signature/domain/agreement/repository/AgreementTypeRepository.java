package swm.toy.signature.domain.agreement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.agreement.entity.AgreementTypeEntity;

public interface AgreementTypeRepository extends JpaRepository<AgreementTypeEntity, Long> {
}
