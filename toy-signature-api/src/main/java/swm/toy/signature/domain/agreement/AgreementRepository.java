package swm.toy.signature.domain.agreement;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    // Pagination 문제 발생함 > OneToMany 관계 때문
    @EntityGraph("fetch-author-agreementType-agreedEquip")
    @Query("SELECT a FROM AgreementEntity a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<AgreementEntity> findByAuthorIdOrderByCreatedAtDesc(
            @Param("id") Long id, Pageable pageable);
}
