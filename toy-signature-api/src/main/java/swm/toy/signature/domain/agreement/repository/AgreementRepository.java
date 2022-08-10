package swm.toy.signature.domain.agreement.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swm.toy.signature.domain.agreement.entity.AgreementEntity;
import swm.toy.signature.domain.equip.entity.EquipEntity;

import java.util.List;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    @EntityGraph("fetch-author-agreementType-agreedEquip")
    @Query("SELECT a FROM AgreementEntity a WHERE a.author.id = :id ORDER BY a.createdAt DESC")
    List<AgreementEntity> findByAuthorIdOrderByCreatedAtDesc(@Param("id") Long id, Pageable pageable);
}
