package swm.toy.signature.application.item;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Value;
import swm.toy.signature.application.item.itemBrand.ItemBrandModel.ItemBrandModelNested;
import swm.toy.signature.application.item.itemType.ItemTypeModel.ItemTypeModelNested;
import swm.toy.signature.application.user.ProfileModel.ProfileModelNested;
import swm.toy.signature.domain.item.Item;

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
        String etc;
        String status;
        ItemTypeModelNested itemType;
        ItemBrandModelNested itemBrand;
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
                    contents.getEtc(),
                    item.getStatus().getValue(),
                    ItemTypeModelNested.fromItemType(item.getItemType()),
                    ItemBrandModelNested.fromItemBrand(item.getItemBrand()),
                    item.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    item.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    ProfileModelNested.fromProfile(item.getAuthor().getProfile()));
        }
    }
}
