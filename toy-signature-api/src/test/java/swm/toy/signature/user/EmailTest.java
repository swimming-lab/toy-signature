package swm.toy.signature.user;

import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void when_same_address_expect_equal_and_hashCode() {
        final var email = new Email("user@email.com");
        final var sameEmail = new Email("user@email.com");

        assertThat(email).isEqualTo(sameEmail).hasSameHashCodeAs(sameEmail);
    }
}
