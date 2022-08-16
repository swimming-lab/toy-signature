package swm.toy.signature.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.signature.domain.user.Email;
import swm.toy.signature.domain.user.UserName;
import swm.toy.signature.domain.user.UserSignUpRequest;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class UserPostRequestDTO {

    @javax.validation.constraints.Email private String email;
    @NotBlank private String username;
    @NotBlank private String password;

    UserSignUpRequest toSignUpRequest() {
        return new UserSignUpRequest(new Email(email), new UserName(username), password);
    }
}
