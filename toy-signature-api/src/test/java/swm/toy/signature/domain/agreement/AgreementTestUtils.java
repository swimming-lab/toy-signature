package swm.toy.signature.domain.agreement;

import org.springframework.test.util.ReflectionTestUtils;
import swm.toy.signature.domain.item.Item;
import swm.toy.signature.domain.item.ItemContents;

import java.time.LocalDateTime;

import static swm.toy.signature.domain.item.brand.ItemBrandTestUtils.databaseItemBrand;
import static swm.toy.signature.domain.item.type.ItemTypeTestUtils.databaseItemType;
import static swm.toy.signature.domain.user.UserTestUtils.databaseUser;

public class AgreementTestUtils {

    public static Agreement databaseAgreement() {
        return databaseAgreement(1L);
    }

    public static Agreement databaseAgreement(Long id) {
        Agreement agreement = Agreement.of(
                databaseUser(),
                AgreementContents.of(
                        Lessor.of(101L, "", "", ""),
                        Lessee.of(102L, "", "", ""),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        100000L,
                        20000L,
                        null));
        ReflectionTestUtils.setField(agreement, "id", id);
        return agreement;
    }
}
