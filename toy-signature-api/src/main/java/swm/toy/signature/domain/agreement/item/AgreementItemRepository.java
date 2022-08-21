package swm.toy.signature.domain.agreement.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementItemRepository extends JpaRepository<AgreementItem, Long> {
    //    @EntityGraph(
    //            attributePaths = {"agreement"},
    //            type = EntityGraph.EntityGraphType.FETCH)
    //    @Query(
    //            "SELECT ae FROM AgreementItem ae LEFT JOIN ae.agreement a WHERE a.author.id = :id
    // ORDER BY a.createdAt DESC")
    //    List<AgreementItem> findByAuthorIdOrderByCreatedAtDesc(@Param("id") Long id, Pageable
    // pageable);
    //
    //    List<AgreementItem> findAllByIdAndIn(List<Long> ids);
}
