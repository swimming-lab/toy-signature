package swm.toy.signature.infrastructure.jwt;

import static java.lang.String.format;
import static java.time.Instant.now;
import static java.util.regex.Pattern.compile;
import static swm.toy.signature.infrastructure.jwt.Base64URL.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Pattern;
import swm.toy.signature.domain.jwt.JWTDeserializer;
import swm.toy.signature.domain.jwt.JWTPayload;
import swm.toy.signature.domain.jwt.JWTSerializer;
import swm.toy.signature.domain.user.User;

class HmacSHA256JWTService implements JWTSerializer, JWTDeserializer {

    private static final String JWT_HEADER = base64URLFromString("{\"alg\":\"HS256\",\"type\":\"JWT\"}");
    private static final String BASE64URL_PATTERN = "[\\w_\\-]+";
    private static final Pattern JWT_PATTERN =
            compile(format("^(%s\\.)(%s\\.)(%s)$", BASE64URL_PATTERN, BASE64URL_PATTERN, BASE64URL_PATTERN));

    private final byte[] secret;
    private final long durationSeconds;
    private final ObjectMapper objectMapper;

    HmacSHA256JWTService(byte[] secret, long durationSeconds, ObjectMapper objectMapper) {
        this.secret = secret;
        this.durationSeconds = durationSeconds;
        this.objectMapper = objectMapper;
    }

    @Override
    public String jwtFromUser(User user) {
        final var messageToSign = JWT_HEADER.concat(".").concat(jwtPayloadFromUser(user));
        final var signature = HmacSHA256.sign(secret, messageToSign);
        return messageToSign.concat(".").concat(base64URLFromBytes(signature));
    }

    private String jwtPayloadFromUser(User user) {
        var jwtPayload = UserJWTPayload.of(user, now().getEpochSecond() + durationSeconds);
        return base64URLFromString(jwtPayload.toString());
    }

    @Override
    public JWTPayload jwtPayloadFromJWT(String jwtToken) {
        if (!JWT_PATTERN.matcher(jwtToken).matches()) {
            throw new IllegalArgumentException("Malformed JWT: " + jwtToken);
        }

        final var splintedTokens = jwtToken.split("\\.");
        if (!splintedTokens[0].equals(JWT_HEADER)) {
            throw new IllegalArgumentException("Malformed JWT! Token must starts with header: " + JWT_HEADER);
        }

        final var signatureBytes =
                HmacSHA256.sign(secret, splintedTokens[0].concat(".").concat(splintedTokens[1]));
        if (!base64URLFromBytes(signatureBytes).equals(splintedTokens[2])) {
            throw new IllegalArgumentException("Token has invalid signature: " + jwtToken);
        }

        try {
            final var decodedPayload = stringFromBase64URL(splintedTokens[1]);
            final var jwtPayload = objectMapper.readValue(decodedPayload, UserJWTPayload.class);
            if (jwtPayload.isExpired()) {
                throw new IllegalArgumentException("Token expired");
            }
            return jwtPayload;
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
