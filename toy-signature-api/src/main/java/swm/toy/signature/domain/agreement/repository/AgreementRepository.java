package swm.toy.signature.domain.agreement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.agreement.entity.AgreementEntity;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
}
