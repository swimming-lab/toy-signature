package swm.toy.signature.domain.agreement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.user.User;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AgreementTest {

    @Mock
    private AgreementContents contents;

    @Mock
    private User author;

    @Spy
    private Agreement agreement;

    @Test
    void when_create_agreement_getStatus_return_rent() {
        final var agreement = Agreement.of(author, contents);

        assertThat(agreement.getStatus()).isEqualTo(AgreementStatus.PENDING);
    }

    @Test
    void when_update_status_return_ing(
            @Mock AgreementUpdateRequest request) {
        given(request.getStatusToUpdate()).willReturn(AgreementStatus.ING.getValue());

        agreement.updateStatus(request);

        assertTrue(agreement.getStatus() == AgreementStatus.ING);
    }
}
