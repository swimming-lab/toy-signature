package swm.toy.signature.domain.equip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equip.entity.EquipCodeEntity;

public interface EquipCodeRepository extends JpaRepository<EquipCodeEntity, Long> {
}
