package swm.toy.signature.domain.item.type;

import org.springframework.test.util.ReflectionTestUtils;

public class ItemTypeTestUtils {

    public static ItemType databaseItemType() {
        final var type = ItemType.of("truck", "10t");
        ReflectionTestUtils.setField(type, "id", 100L);
        return type;
    }
}
