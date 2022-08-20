package swm.toy.signature.domain.agreement.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgreementItemRepository extends JpaRepository<AgreementItem, Long> {
    @EntityGraph(
            attributePaths = {"agreement"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Query(
            "SELECT ae FROM AgreementItem ae LEFT JOIN ae.agreement a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<AgreementItem> findByAuthorIdOrderByCreatedAtDesc(
            @Param("id") Long id, Pageable pageable);
}
