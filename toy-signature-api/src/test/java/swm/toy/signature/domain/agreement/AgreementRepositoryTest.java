package swm.toy.signature.domain.agreement;

import static swm.toy.signature.domain.user.UserTestUtils.databaseUser;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@DataJpaTest
class AgreementRepositoryTest {

    @Autowired private AgreementRepository agreementRepository;

    @Test
    void when_save_article_expect_auditing_works() {
        var contentsToSave =
                AgreementContents.of(
                        Lessor.of(101L, "", "", ""),
                        Lessee.of(102L, "", "", ""),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        100000L,
                        20000L,
                        null);

        //        var agreementToSave =
        // userFindService.findById(101L).get().createAgreement(contentsToSave);
        var agreementToSave = databaseUser().createAgreement(contentsToSave);
        //        agreementToSave.addAgreementItems(AgreementItem.of());
        var agreementSaved = agreementRepository.save(agreementToSave);

        //        assertThat(articleSaved).hasNoNullFieldsOrProperties();
    }

    //    @Test
    //    void when_ListOfAuthorId_then_ReturnAgreements_HaveAuthorIdAndOrderByCreatedAtDesc() {
    //        agreement2 = AgreementEntity.builder()
    //                .startDate(LocalDateTime.now())
    //                .endDate(LocalDateTime.now())
    //                .lessorId(user1.getId())
    //                .lessorName(user1.getName())
    //                .lessorTelNo("010-5555-4444")
    //                .lessorAddr("서울시 동작구")
    //                .lesseeId(user2.getId())
    //                .lesseeName(user2.getName())
    //                .lesseeTelNo("010-4444-5555")
    //                .lesseeAddr("서울시 마포구")
    //                .status("0")
    //                .amount(1000000L)
    //                .overAmount(500000L)
    //                .etc("")
    //                .author(user1)
    //                .agreementType(agreementType)
    //                .build();
    //
    //        agreement3 = AgreementEntity.builder()
    //                .startDate(LocalDateTime.now())
    //                .endDate(LocalDateTime.now())
    //                .lessorId(user1.getId())
    //                .lessorName(user1.getName())
    //                .lessorTelNo("010-3333-2222")
    //                .lessorAddr("서울시 동작구")
    //                .lesseeId(user2.getId())
    //                .lesseeName(user2.getName())
    //                .lesseeTelNo("010-2222-3333")
    //                .lesseeAddr("서울시 마포구")
    //                .status("0")
    //                .amount(1000000L)
    //                .overAmount(500000L)
    //                .etc("")
    //                .author(user1)
    //                .agreementType(agreementType)
    //                .build();
    //
    //        agreementRepository.saveAll(List.of(agreement2, agreement3));
    //
    //        agreedEquip2 = AgreedEquipEntity.builder()
    //                .licensePlate(equipEntity.getLicensePlate())
    //                .insuranceYn(equipEntity.getInsuranceYn())
    //                .routineYn(equipEntity.getRoutineYn())
    //                .type(equipTypeEntity.getType())
    //                .heavy(equipTypeEntity.getHeavy())
    //                .brand(equipBrandEntity.getBrandName())
    //                .agreement(agreement2)
    //                .equip(equipEntity)
    //                .build();
    //        agreedEquip2 = agreedEquipRepository.save(agreedEquip2);
    //
    //        agreedEquip3 = AgreedEquipEntity.builder()
    //                .licensePlate(equipEntity.getLicensePlate())
    //                .insuranceYn(equipEntity.getInsuranceYn())
    //                .routineYn(equipEntity.getRoutineYn())
    //                .type(equipTypeEntity.getType())
    //                .heavy(equipTypeEntity.getHeavy())
    //                .brand(equipBrandEntity.getBrandName())
    //                .agreement(agreement3)
    //                .equip(equipEntity)
    //                .build();
    //        agreedEquipRepository.saveAll(List.of(agreedEquip2, agreedEquip3));
    //
    //        // TODO N+1, Pagination 해결
    //        // firstResult/maxResults specified with collection fetch; applying in memory!
    //        List<AgreedEquipEntity> actual =
    // agreedEquipRepository.findByAuthorIdOrderByCreatedAtDesc(user1.getId(), PageRequest.of(0,
    // 3));
    //        actual.stream().forEach(a -> System.out.println(a.getAgreement()));
    //        assertEquals(3, actual.size());
    //        assertEquals(actual.get(0).getId(), 1L);
    //        assertEquals(actual.get(1).getId(), 2L);
    //        assertEquals(actual.get(2).getId(), 3L);
    //    }
}
