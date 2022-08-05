package swm.toy.signature.domain.equip.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;

public interface EquipTypeRepository extends JpaRepository<EquipTypeEntity, Long> {
}
