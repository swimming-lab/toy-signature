package swm.toy.signature.domain.equip.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swm.toy.signature.domain.equip.entity.EquipEntity;

import java.util.List;

public interface EquipRepository extends JpaRepository<EquipEntity, Long> {
    @EntityGraph("fetch-author-equipType-equipBrand")
    @Query("SELECT e FROM EquipEntity e WHERE e.author.id = :id ORDER BY e.sequence ASC")
    List<EquipEntity> findByAuthorIdOrderBySequenceAsc(@Param("id") Long id, Pageable pageable);
}
