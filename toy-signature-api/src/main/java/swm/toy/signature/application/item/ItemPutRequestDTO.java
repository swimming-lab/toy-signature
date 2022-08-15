package swm.toy.signature.application.item;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;
import static swm.toy.signature.domain.item.ItemUpdateRequest.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.item.ItemUpdateRequest;
import swm.toy.signature.domain.item.brand.ItemBrand;
import swm.toy.signature.domain.item.type.ItemType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("item")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class ItemPutRequestDTO {

    @NotNull private Long id;
    private String licensePlate;
    private Integer sequence;
    private String insurance;
    private String routine;
    private ItemType itemType;
    private ItemBrand itemBrand;

    ItemUpdateRequest toUpdateRequest() {
        return builder()
                .licensePlateToUpdate(ofNullable(licensePlate).orElse(null))
                .sequenceToUpdate(ofNullable(sequence).orElse(null))
                .insuranceYnToUpdate(ofNullable(insurance).orElse(null))
                .routineYnToUpdate(ofNullable(routine).orElse(null))
                .itemTypeToUpdate(ofNullable(itemType).orElse(null))
                .itemBrandToUpdate(ofNullable(itemBrand).orElse(null))
                .build();
    }
}
