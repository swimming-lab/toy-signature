package swm.toy.signature.domain.agreement;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    // Pagination 문제 발생함 > OneToMany 관계 때문
    //    @Query("SELECT a FROM Agreement a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<Agreement> findByAuthorIdOrderByCreatedAtDesc(@Param("id") Long id, Pageable pageable);

    //    @EntityGraph(attributePaths = {"author", "agreementItems"})
    //    @EntityGraph(attributePaths = {"author"})
    List<Agreement> findAllByAuthorId(long authorId, Pageable pageable);

    List<Agreement> findAllByAuthorIdAndStatus(long authorId, AgreementStatus status, Pageable pageable);
}
