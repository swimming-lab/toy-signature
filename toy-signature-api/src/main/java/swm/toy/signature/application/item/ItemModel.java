package swm.toy.signature.application.item;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Value;
import swm.toy.signature.application.user.ProfileModel.ProfileModelNested;
import swm.toy.signature.domain.item.Item;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Value
class ItemModel {

    ItemModelNested item;

    static ItemModel fromItem(Item item) {
        return new ItemModel(ItemModelNested.fromItem(item));
    }

    @Value
    static class ItemModelNested {
        String licensePlate;
        Integer sequence;
        String insuranceYn;
        String routineYn;
        ItemType itemType;
        ItemBrand itemBrand;
        ZonedDateTime createdAt;
        ZonedDateTime updatedAt;
        ProfileModelNested author;

        static ItemModelNested fromItem(Item item) {
            final var contents = item.getContents();
            return new ItemModelNested(
                    contents.getLicensePlate(),
                    contents.getSequence(),
                    contents.getInsuranceYn(),
                    contents.getRoutineYn(),
                    item.getItemType(),
                    item.getItemBrand(),
                    item.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    item.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    ProfileModelNested.fromProfile(item.getAuthor().getProfile()));
        }
    }
}
