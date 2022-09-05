package swm.toy.signature.domain.agreement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.agreement.item.AgreementItemService;
import swm.toy.signature.domain.item.ItemFindService;
import swm.toy.signature.domain.user.User;
import swm.toy.signature.domain.user.UserFindService;
import swm.toy.signature.domain.user.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static swm.toy.signature.domain.user.UserTestUtils.databaseUser;

@ExtendWith(MockitoExtension.class)
class AgreementServiceTest {

    private AgreementService agreementService;

    @Mock
    private AgreementRepository agreementRepository;

    @Mock
    private AgreementItemService agreementItemService;

    @Mock
    private UserFindService userFindService;

    @Mock
    private ItemFindService itemFindService;

    @Spy
    private User author;

    @Spy
    private Agreement agreement;

    @BeforeEach
    void initializeUserService() {
        this.agreementService =
                new AgreementService(agreementRepository, agreementItemService, userFindService, itemFindService);
    }

    @Test
    void when_author_not_found_expect_NoSuchElementException(@Mock AgreementCreateRequest request) {
        when(userFindService.findById(anyLong())).thenReturn(empty());

        assertThatThrownBy(() -> agreementService.createAgreement(1L, request))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void when_item_not_found_expect_NoSuchElementException(@Mock AgreementCreateRequest request) {
        when(request.getItemIds()).thenReturn(Set.of(1L));
        when(itemFindService.getItemByIdAndStatus(anyLong())).thenReturn(empty());

        assertThatThrownBy(() -> agreementService.createAgreement(1L, request))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void given_author_createAgreement_then_userRepository_save(
            @Mock AgreementCreateRequest request, @Mock Agreement agreement) {
        given(userFindService.findById(1L)).willReturn(of(author));
        given(author.createAgreement(request.getAgreementContents())).willReturn(agreement);
        given(agreementRepository.save(agreement)).willReturn(agreement);

        agreementService.createAgreement(1L, request);

        then(agreementRepository).should(times(1)).save(agreement);
    }

    @Test
    void given_agreement_updateAgreementStatus_then_agreementRepository_save(
            @Mock AgreementUpdateRequest request) {
        given(request.getStatusToUpdate()).willReturn(AgreementStatus.ING.getValue());
        given(request.getAgreementId()).willReturn(1L);
        given(userFindService.findById(1L)).willReturn(of(author));
        given(agreementRepository.findById(1L)).willReturn(of(agreement));

        Agreement updated = agreementService.updateAgreementStatus(1L, request);

        assertTrue(updated.getStatus() == AgreementStatus.ING);
    }
}
