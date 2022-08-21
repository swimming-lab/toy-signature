package swm.toy.signature.domain.agreement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.user.User;

@ExtendWith(MockitoExtension.class)
class AgreementTest {

    @Mock private AgreementContents contents;
    @Mock private User author;

    @Test
    void when_create_agreement_getStatus_return_rent() {
        final var agreement = Agreement.of(author, contents);

        assertThat(agreement.getStatus()).isEqualTo(AgreementStatus.PENDING);
    }
}
