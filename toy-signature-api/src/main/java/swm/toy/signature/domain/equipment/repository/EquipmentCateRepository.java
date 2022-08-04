package swm.toy.signature.domain.equipment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equipment.entity.EquipmentCateEntity;

public interface EquipmentCateRepository extends JpaRepository<EquipmentCateEntity, Long> {
}
