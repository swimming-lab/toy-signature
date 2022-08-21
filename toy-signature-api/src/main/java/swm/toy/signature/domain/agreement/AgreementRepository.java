package swm.toy.signature.domain.agreement;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    // Pagination 문제 발생함 > OneToMany 관계 때문
    @EntityGraph(attributePaths = {"author", "agreementType", "agreementItems"})
    //    @Query("SELECT a FROM Agreement a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<Agreement> findByAuthorIdOrderByCreatedAtDesc(@Param("id") Long id, Pageable pageable);
}
