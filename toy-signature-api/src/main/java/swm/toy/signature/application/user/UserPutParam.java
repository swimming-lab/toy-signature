package swm.toy.signature.application.user;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.user.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class UserPutParam {

    private String email;
    private String username;
    private String password;
    private String image;
    private String status;
    private String phone;
    private List<Long> idList;
    private Long[] ids;

    UserUpdateRequest toUpdateRequest() {
        return UserUpdateRequest.builder()
                .emailToUpdate(ofNullable(email).map(Email::new).orElse(null))
                .userNameToUpdate(ofNullable(username).map(UserName::new).orElse(null))
                .imageToUpdate(ofNullable(image).map(Image::new).orElse(null))
                .passwordToUpdate(password)
                .statusToUpdate(status)
                .phoneToUpdate(ofNullable(phone).map(Phone::new).orElse(null))
                .build();
    }
}
