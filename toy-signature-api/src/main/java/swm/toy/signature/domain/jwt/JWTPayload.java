package swm.toy.signature.domain.jwt;

import java.io.Serializable;

public interface JWTPayload extends Serializable {

    long getUserId();

    String getAuth();

    boolean isExpired();
}
