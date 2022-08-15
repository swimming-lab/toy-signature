package swm.toy.signature.application.admin;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("itemBrand")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public class ItemBrandRequestDTO {

    @NotBlank private String brandName;

    //    UserSignUpRequest toItemTypeCreateRequest() {
    //        return new ItemTypeCreateRequest(type, heavy);
    //    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotNull private Long id;
        private String brandName;
    }
}
