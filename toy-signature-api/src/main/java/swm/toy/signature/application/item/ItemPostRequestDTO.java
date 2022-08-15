package swm.toy.signature.application.item;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.item.ItemContents;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("article")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class ItemPostRequestDTO {

    @NotNull private String licensePlate;
    @NotNull private Integer sequence;
    @NotNull private String insurance;
    @NotNull private String routine;
    @NotNull private ItemType itemType;
    @NotNull private ItemBrand itemBrand;

    ItemContents toItemContents() {
        return new ItemContents(licensePlate, sequence, insurance, routine, itemType, itemBrand);
    }
}
