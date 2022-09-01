package swm.toy.signature.application.user;

import lombok.Value;
import swm.toy.signature.domain.user.User;

import static java.lang.String.valueOf;

@Value
public class UserModel {

    String email;
    String username;
    String token;

    public static UserModel fromUserAndToken(User user, String token) {
        return new UserModel(valueOf(user.getEmail()), valueOf(user.getName()), token);
    }
}
