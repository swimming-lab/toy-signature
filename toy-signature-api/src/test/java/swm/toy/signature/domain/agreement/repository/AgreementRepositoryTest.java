package swm.toy.signature.domain.agreement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import swm.toy.signature.domain.agreement.entity.AgreementEntity;
import swm.toy.signature.domain.agreement.entity.AgreementTypeEntity;
import swm.toy.signature.domain.user.entity.UserEntity;
import swm.toy.signature.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class AgreementRepositoryTest {
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    AgreementTypeRepository agreementTypeRepository;
    @Autowired
    UserRepository userRepository;

    private AgreementTypeEntity agreementType;
    private UserEntity user1;
    private UserEntity user2;
    private AgreementEntity agreement1;
    private AgreementEntity agreement2;
    private AgreementEntity agreement3;

    @BeforeEach
    void setUp() {
        agreementType = AgreementTypeEntity.builder()
                .type("임대계약서")
                .build();
        agreementType = agreementTypeRepository.save(agreementType);

        user1 = UserEntity.builder()
                .name("lessor")
                .email("test1@test.com")
                .password("password")
                .build();
        user2 = UserEntity.builder()
                .name("lessee")
                .email("test2@test.com")
                .password("password")
                .build();
        userRepository.saveAll(List.of(user1, user2));

        agreement1 = AgreementEntity.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .lessorId(user1.getId())
                .lessorName(user1.getName())
                .lessorTelNo("010-1234-1234")
                .lessorAddr("서울시 동작구")
                .lesseeId(user2.getId())
                .lesseeName(user2.getName())
                .lesseeTelNo("010-5555-6666")
                .lesseeAddr("서울시 마포구")
                .status("0")
                .amount(1000000L)
                .overAmount(500000L)
                .etc("")
                .author(user1)
                .agreementType(agreementType)
                .build();
        agreement1 = agreementRepository.save(agreement1);
    }
}