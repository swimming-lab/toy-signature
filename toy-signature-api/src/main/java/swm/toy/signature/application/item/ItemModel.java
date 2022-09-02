package swm.toy.signature.application.item;

import lombok.Value;
import swm.toy.signature.application.item.itemBrand.ItemBrandModel.ItemBrandModelNested;
import swm.toy.signature.application.item.itemType.ItemTypeModel.ItemTypeModelNested;
import swm.toy.signature.application.user.ProfileModel.ProfileModelNested;
import swm.toy.signature.domain.item.Item;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Value
public class ItemModel {

    ItemModelNested item;

    public static ItemModel from(Item item) {
        return new ItemModel(ItemModelNested.from(item));
    }

    @Value
    public static class ItemModelNested {
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

        public static ItemModelNested from(Item item) {
            final var contents = item.getContents();
            return new ItemModelNested(
                    contents.getLicensePlate(),
                    contents.getSequence(),
                    contents.getInsuranceYn(),
                    contents.getRoutineYn(),
                    contents.getEtc(),
                    item.getStatus().getValue(),
                    ItemTypeModelNested.from(item.getItemType()),
                    ItemBrandModelNested.from(item.getItemBrand()),
                    item.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    item.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")),
                    ProfileModelNested.from(item.getAuthor().getProfile()));
        }
    }
}
