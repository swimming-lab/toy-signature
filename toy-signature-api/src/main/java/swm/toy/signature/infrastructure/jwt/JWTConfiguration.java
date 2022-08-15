package swm.toy.signature.infrastructure.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JWTConfiguration {

    private static final byte[] SECRET = "SOME_SIGNATURE_SECRET".getBytes(StandardCharsets.UTF_8);
    private static final int JWT_DURATION_SECONDS = 2 * 60 * 60;

    @Bean
    HmacSHA256JWTService hmacSHA256JWTService(ObjectMapper objectMapper) {
        return new HmacSHA256JWTService(SECRET, JWT_DURATION_SECONDS, objectMapper);
    }
}
