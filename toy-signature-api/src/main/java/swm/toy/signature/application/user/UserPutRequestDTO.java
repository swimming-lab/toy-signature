package swm.toy.signature.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.user.Email;
import swm.toy.signature.domain.user.Image;
import swm.toy.signature.domain.user.UserName;
import swm.toy.signature.domain.user.UserUpdateRequest;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class UserPutRequestDTO {

    private String email;
    private String username;
    private String password;
    private String image;

    UserUpdateRequest toUpdateRequest() {
        return UserUpdateRequest.builder()
                .emailToUpdate(ofNullable(email).map(Email::new).orElse(null))
                .userNameToUpdate(ofNullable(username).map(UserName::new).orElse(null))
                .imageToUpdate(ofNullable(image).map(Image::new).orElse(null))
                .passwordToUpdate(password)
                .build();
    }
}
