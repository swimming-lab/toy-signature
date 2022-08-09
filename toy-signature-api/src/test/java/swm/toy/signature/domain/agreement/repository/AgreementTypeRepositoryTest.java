package swm.toy.signature.domain.agreement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import swm.toy.signature.domain.agreement.entity.AgreementTypeEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AgreementTypeRepositoryTest {
    @Autowired
    AgreementTypeRepository agreementTypeRepository;

    private AgreementTypeEntity svedAgreementType;

    @BeforeEach
    void setUp() {
        svedAgreementType = AgreementTypeEntity.builder()
                .type("임대계약서")
                .build();

        svedAgreementType = agreementTypeRepository.save(svedAgreementType);
    }

    @Test
    void whenSave_thenCanBeFound() {
        Long id = svedAgreementType.getId();

        Optional<AgreementTypeEntity> maybeAgreementType = agreementTypeRepository.findById(id);

        assertTrue(maybeAgreementType.isPresent());
    }
}