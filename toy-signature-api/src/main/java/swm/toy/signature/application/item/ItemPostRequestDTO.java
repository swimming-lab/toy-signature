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
import swm.toy.signature.domain.item.ItemCreateRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("item")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class ItemPostRequestDTO {

    @NotNull private String licensePlate;
    @NotNull private Integer sequence;
    @NotNull private String insuranceYn;
    @NotNull private String routineYn;
    @NotNull private Long itemTypeId;
    @NotNull private Long itemBrandId;

    ItemCreateRequest toItemCreateRequest() {
        return new ItemCreateRequest(
                new ItemContents(licensePlate, sequence, insuranceYn, routineYn),
                itemTypeId,
                itemBrandId);
    }
}
