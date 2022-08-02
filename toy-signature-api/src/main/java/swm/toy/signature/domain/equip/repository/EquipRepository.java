package swm.toy.signature.domain.equip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equip.entity.EquipEntity;

public interface EquipRepository extends JpaRepository<EquipEntity, Long> {
}
