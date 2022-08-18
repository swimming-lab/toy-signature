package swm.toy.signature.application.item;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.item.ItemUpdateRequest;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("item")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class ItemPutRequestDTO {

    @NotNull private Long id;
    private String licensePlate;
    private Integer sequence;
    private String insuranceYn;
    private String routineYn;
    private Long itemTypeId;
    private Long itemBrandId;

    ItemUpdateRequest toUpdateRequest() {
        return ItemUpdateRequest.builder()
                .itemId(id)
                .licensePlateToUpdate(ofNullable(licensePlate).orElse(null))
                .sequenceToUpdate(ofNullable(sequence).orElse(null))
                .insuranceYnToUpdate(ofNullable(insuranceYn).orElse(null))
                .routineYnToUpdate(ofNullable(routineYn).orElse(null))
                .itemTypeIdToUpdate(ofNullable(itemTypeId).orElse(null))
                .itemBrandIdToUpdate(ofNullable(itemBrandId).orElse(null))
                .build();
    }
}
