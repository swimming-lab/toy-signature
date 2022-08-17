package swm.toy.signature.application.item;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.item.ItemUpdateRequest;

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
    private Long itemTypeId;
    private Long itemBrandId;

    ItemUpdateRequest toUpdateRequest() {
        return ItemUpdateRequest.builder()
                .licensePlateToUpdate(ofNullable(licensePlate).orElse(null))
                .sequenceToUpdate(ofNullable(sequence).orElse(null))
                .insuranceYnToUpdate(ofNullable(insurance).orElse(null))
                .routineYnToUpdate(ofNullable(routine).orElse(null))
                .itemTypeIdToUpdate(ofNullable(itemTypeId).orElse(null))
                .itemBrandIdToUpdate(ofNullable(itemBrandId).orElse(null))
                .build();
    }
}
