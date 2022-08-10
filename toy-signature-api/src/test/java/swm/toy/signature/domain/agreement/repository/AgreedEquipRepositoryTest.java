package swm.toy.signature.domain.agreement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import swm.toy.signature.domain.agreement.entity.AgreedEquipEntity;
import swm.toy.signature.domain.agreement.entity.AgreementEntity;
import swm.toy.signature.domain.agreement.entity.AgreementTypeEntity;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.entity.EquipEntity;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
import swm.toy.signature.domain.equip.repository.EquipRepository;
import swm.toy.signature.domain.equip.repository.EquipTypeRepository;
import swm.toy.signature.domain.user.entity.UserEntity;
import swm.toy.signature.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgreedEquipRepositoryTest {
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    AgreementTypeRepository agreementTypeRepository;
    @Autowired
    AgreedEquipRepository agreedEquipRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EquipRepository equipRepository;
    @Autowired
    EquipTypeRepository equipTypeRepository;
    @Autowired
    EquipBrandRepository equipBrandRepository;

    private AgreementTypeEntity agreementType;
    private UserEntity user1;
    private UserEntity user2;
    private AgreementEntity agreement1;
    private AgreementEntity agreement2;
    private AgreementEntity agreement3;
    private AgreedEquipEntity agreedEquip1;
    private EquipBrandEntity equipBrandEntity;
    private EquipTypeEntity equipTypeEntity;
    private EquipEntity equipEntity;

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

        equipBrandEntity = EquipBrandEntity.builder()
                .brandName("닛산")
                .build();
        equipBrandRepository.save(equipBrandEntity);

        equipTypeEntity = EquipTypeEntity.builder()
                .type("포크레인")
                .heavy("5톤")
                .build();
        equipTypeRepository.save(equipTypeEntity);

        equipEntity = EquipEntity.builder()
                .licensePlate("62주7416")
                .sequence(1)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipBrand(equipBrandEntity)
                .equipType(equipTypeEntity)
                .author(user1)
                .build();

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

        agreedEquip1 = AgreedEquipEntity.builder()
                .licensePlate(equipEntity.getLicensePlate())
                .insuranceYn(equipEntity.getInsuranceYn())
                .routineYn(equipEntity.getRoutineYn())
                .type(equipTypeEntity.getType())
                .heavy(equipTypeEntity.getHeavy())
                .brand(equipBrandEntity.getBrandName())
                .agreement(agreement1)
                .equip(equipEntity)
                .build();

        agreement1 = agreementRepository.save(agreement1);
    }

    @Test
    void whenListOfAuthorId_thenReturnAgreementsHaveAuthorIdAndOrderByCreatedAtDesc() {
        agreement2 = AgreementEntity.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .lessorId(user1.getId())
                .lessorName(user1.getName())
                .lessorTelNo("010-5555-4444")
                .lessorAddr("서울시 동작구")
                .lesseeId(user2.getId())
                .lesseeName(user2.getName())
                .lesseeTelNo("010-4444-5555")
                .lesseeAddr("서울시 마포구")
                .status("0")
                .amount(1000000L)
                .overAmount(500000L)
                .etc("")
                .author(user1)
                .agreementType(agreementType)
                .build();

        agreement3 = AgreementEntity.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .lessorId(user1.getId())
                .lessorName(user1.getName())
                .lessorTelNo("010-3333-2222")
                .lessorAddr("서울시 동작구")
                .lesseeId(user2.getId())
                .lesseeName(user2.getName())
                .lesseeTelNo("010-2222-3333")
                .lesseeAddr("서울시 마포구")
                .status("0")
                .amount(1000000L)
                .overAmount(500000L)
                .etc("")
                .author(user1)
                .agreementType(agreementType)
                .build();

        agreementRepository.saveAll(List.of(agreement2, agreement3));

        // TODO page 해결
        // firstResult/maxResults specified with collection fetch; applying in memory!
        List<AgreementEntity> actual = agreedEquipRepository.findByAuthorIdOrderByCreatedAtDesc(user1.getId(), PageRequest.of(0, 3));

        assertEquals(3, actual.size());
        assertEquals(actual.get(0).getId(), 1L);
        assertEquals(actual.get(1).getId(), 2L);
        assertEquals(actual.get(2).getId(), 3L);
    }

}