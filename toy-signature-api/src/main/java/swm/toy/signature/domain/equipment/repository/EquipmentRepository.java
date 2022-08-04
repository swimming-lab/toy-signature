package swm.toy.signature.domain.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equipment.entity.EquipmentEntity;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}
