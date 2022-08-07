package swm.toy.signature.domain.equip.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;

import java.util.Optional;

public interface EquipTypeRepository extends JpaRepository<EquipTypeEntity, Long> {
    Optional<EquipTypeEntity> findByType(String type);
}
