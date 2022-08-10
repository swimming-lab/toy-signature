package swm.toy.signature.domain.agreement.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swm.toy.signature.domain.agreement.entity.AgreementEntity;
import swm.toy.signature.domain.agreement.entity.AgreementTypeEntity;

import java.util.List;

public interface AgreementTypeRepository extends JpaRepository<AgreementTypeEntity, Long> {
}
