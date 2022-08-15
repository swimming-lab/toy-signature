package swm.toy.signature.domain.agreement;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgreedEquipRepository extends JpaRepository<AgreedEquipEntity, Long> {
    @EntityGraph(
            attributePaths = {"agreement"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Query(
            "SELECT ae FROM AgreedEquipEntity ae LEFT JOIN ae.agreement a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<AgreedEquipEntity> findByAuthorIdOrderByCreatedAtDesc(
            @Param("id") Long id, Pageable pageable);
}
