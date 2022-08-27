package swm.toy.signature.domain.item;

import static swm.toy.signature.domain.item.brand.ItemBrandTestUtils.databaseItemBrand;
import static swm.toy.signature.domain.item.type.ItemTypeTestUtils.databaseItemType;
import static swm.toy.signature.domain.user.UserTestUtils.databaseUser;

import org.springframework.test.util.ReflectionTestUtils;

public class ItemTestUtils {

    public static Item databaseItem() {
        return databaseItem(1L);
    }

    public static Item databaseItem(Long id) {
        Item item = Item.of(
                databaseUser(),
                ItemContents.of("test_" + id, 1, "Y", "Y", null),
                databaseItemType(),
                databaseItemBrand());
        ReflectionTestUtils.setField(item, "id", id);
        return item;
    }
}
