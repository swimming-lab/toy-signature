package swm.toy.signature.domain.agreement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.item.Item;
import swm.toy.signature.domain.item.ItemContents;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.brand.ItemBrandRepository;
import swm.toy.signature.domain.item.type.ItemType;
import swm.toy.signature.domain.item.type.ItemTypeRepository;
import swm.toy.signature.domain.user.User;
import swm.toy.signature.domain.user.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swm.toy.signature.domain.item.brand.ItemBrandTestUtils.databaseItemBrand;
import static swm.toy.signature.domain.item.type.ItemTypeTestUtils.databaseItemType;
import static swm.toy.signature.domain.user.UserTestUtils.databaseUser;

@EnableJpaAuditing
@DataJpaTest
class AgreementRepositoryTest {

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemBrandRepository itemBrandRepository;

    private User user;
    private ItemType itemType;
    private ItemBrand itemBrand;

    @BeforeEach
    void setUp() {
        user = userRepository.save(databaseUser());
        itemType = itemTypeRepository.save(databaseItemType());
        itemBrand = itemBrandRepository.save(databaseItemBrand());
    }

    @Test
    void when_save_agreement_expect_auditing_works() {
        var contentsToSave = AgreementContents.of(
                Lessor.of(101L, "", "", ""),
                Lessee.of(102L, "", "", ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                100000L,
                20000L,
                null);

        // userFindService.findById(101L).get().createAgreement(contentsToSave);
        var agreementToSave = user.createAgreement(contentsToSave);
        var agreementSaved = agreementRepository.save(agreementToSave);

        assertNotNull(agreementSaved.getCreatedAt());
        assertNotNull(agreementSaved.getUpdatedAt());
    }

    @Test
    void when_save_agreement_with_item_expect_saved_items() {
        var contentsToSave = AgreementContents.of(
                Lessor.of(101L, "", "", ""),
                Lessee.of(102L, "", "", ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                100000L,
                20000L,
                null);

        Item item1 = Item.of(user, ItemContents.of("1234", 1, "Y", "Y", null), itemType, itemBrand);
        Item item2 = Item.of(user, ItemContents.of("5678", 2, "Y", "Y", null), itemType, itemBrand);

        var agreementToSave = user.createAgreement(contentsToSave);
        agreementToSave.addAgreementItems(AgreementItem.of(item1));
        agreementToSave.addAgreementItems(AgreementItem.of(item2));
        var agreementSaved = agreementRepository.save(agreementToSave);

        assertTrue(agreementSaved.getAgreementItems().size() == 2);
    }

    @Test
    void when_find_by_author_expect_saved_agreements_with_items() {
        saveAgreements(10);

        Page<Agreement> agreements = agreementRepository.findAllByAuthorId(user.getId(), PageRequest.of(0, 10));

        assertTrue(agreements.getTotalElements() == 10);
        assertTrue(agreements
                .get()
                .map(agreement -> agreement.getAgreementItems())
                .anyMatch(agreementItems -> agreementItems.size() == 1));
    }

    @Test
    void test() {
        String status = "PENDING";
        AgreementStatus agreementStatus = AgreementStatus.valueOf(status);
        System.out.println(agreementStatus);
    }

    @Test
    void when_find_by_author_and_status_expect_saved_agreements_with_items() {
        saveAgreements(10);

        Page<Agreement> agreements = agreementRepository.findAllByAuthorIdAndStatus(user.getId(), AgreementStatus.PENDING, PageRequest.of(0, 10));

        assertTrue(agreements.getTotalElements() == 10);
        assertTrue(agreements
                .get()
                .map(agreement -> agreement.getAgreementItems())
                .anyMatch(agreementItems -> agreementItems.size() == 1));
    }

    void saveAgreements(int size) {
        List<Agreement> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var contentsToSave = AgreementContents.of(
                    Lessor.of(101L, "", "", ""),
                    Lessee.of(102L, "", "", ""),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    100000L,
                    20000L,
                    null);

            Item item = Item.of(user, ItemContents.of("test_" + i, 1, "Y", "Y", null), itemType, itemBrand);
            var agreementToSave = user.createAgreement(contentsToSave);
            agreementToSave.addAgreementItems(AgreementItem.of(item));
            list.add(agreementToSave);
        }

        agreementRepository.saveAll(list);
        agreementRepository.flush();
    }
}
