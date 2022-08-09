package swm.toy.signature.domain.agreement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.agreement.entity.AgreedEquipEntity;

public interface AgreedEquipRepository extends JpaRepository<AgreedEquipEntity, Long> {
}
