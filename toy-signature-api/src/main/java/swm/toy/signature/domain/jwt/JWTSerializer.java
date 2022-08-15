package swm.toy.signature.domain.jwt;

import swm.toy.signature.domain.user.User;

public interface JWTSerializer {

    String jwtFromUser(User user);
}
