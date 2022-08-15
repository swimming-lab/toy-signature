package swm.toy.signature.infrastructure.jwt;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.time.Instant.now;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.stream.Collectors;
import swm.toy.signature.domain.jwt.JWTPayload;
import swm.toy.signature.domain.user.Authority;
import swm.toy.signature.domain.user.User;

public class UserJWTPayload implements JWTPayload {

    private final long sub;
    private final String name;
    private final String auth;
    private final long iat;

    static UserJWTPayload of(User user, long epochSecondExpired) {
        String auth =
                user.getAuthorities().stream()
                        .map(Authority::getAuthorityName)
                        .collect(Collectors.joining(","));

        return new UserJWTPayload(user.getId(), valueOf(user.getEmail()), auth, epochSecondExpired);
    }

    UserJWTPayload(
            @JsonProperty("sub") long sub,
            @JsonProperty("name") String name,
            @JsonProperty("auth") String auth,
            @JsonProperty("iat") long iat) {
        this.sub = sub;
        this.name = name;
        this.auth = auth;
        this.iat = iat;
    }

    @Override
    public long getUserId() {
        return sub;
    }

    @Override
    public boolean isExpired() {
        return iat < now().getEpochSecond();
    }

    @Override
    public String getAuth() {
        return auth;
    }

    @Override
    public String toString() {
        return format(
                "{\"sub\":%d,\"name\":\"%s\",\"auth\":\"%s\",\"iat\":%d}", sub, name, auth, iat);
    }
}
