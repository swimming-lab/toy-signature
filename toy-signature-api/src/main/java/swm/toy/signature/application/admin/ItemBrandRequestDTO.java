package swm.toy.signature.application.admin;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("itemBrand")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class ItemBrandRequestDTO {

    @NotBlank private String brandName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Update {
        @NotNull private Long id;
        private String brandName;
    }
}
