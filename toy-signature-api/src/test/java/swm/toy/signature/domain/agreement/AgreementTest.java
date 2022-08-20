package swm.toy.signature.domain.agreement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.agreement.type.AgreementType;
import swm.toy.signature.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AgreementTest {

    @Mock
    private AgreementContents contents;
    @Mock
    private AgreementType agreementType;
    @Mock
    private User author;

    @Test
    void when_create_agreement_getStatus_return_rent() {
        final var agreement = new Agreement(author, contents, agreementType);

        assertThat(agreement.getStatus()).isEqualTo(AgreementStatus.RENT);
    }
}