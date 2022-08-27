package swm.toy.signature.domain.item.brand;

import org.springframework.test.util.ReflectionTestUtils;

public class ItemBrandTestUtils {

    public static ItemBrand databaseItemBrand() {
        final var brand = ItemBrand.from("testBrand");
        ReflectionTestUtils.setField(brand, "id", 100L);
        return brand;
    }
}
