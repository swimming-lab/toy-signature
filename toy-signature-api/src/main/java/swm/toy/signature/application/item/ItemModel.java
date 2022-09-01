package swm.toy.signature.application.item;

import lombok.Value;
import swm.toy.signature.application.item.itemBrand.ItemBrandModel;
import swm.toy.signature.application.item.itemType.ItemTypeModel;
import swm.toy.signature.application.user.ProfileModel;
import swm.toy.signature.domain.item.Item;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ItemModel {

    String licensePlate;
    Integer sequence;
    String insuranceYn;
    String routineYn;
    String etc;
    String status;
    ItemTypeModel itemType;
    ItemBrandModel itemBrand;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ProfileModel author;

    public static ItemModel from(Item item) {
        final var contents = item.getContents();
        return new ItemModel(
                contents.getLicensePlate(),
                contents.getSequence(),
                contents.getInsuranceYn(),
                contents.getRoutineYn(),
                contents.getEtc(),
                item.getStatus().getValue(),
                ItemTypeModel.from(item.getItemType()),
                ItemBrandModel.from(item.getItemBrand()),
                item.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")),
                item.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")),
                ProfileModel.from(item.getAuthor().getProfile()));
    }

    public static List<ItemModel> fromList(List<Item> items) {
        return items.stream().map(ItemModel::from).collect(Collectors.toList());
    }
}
