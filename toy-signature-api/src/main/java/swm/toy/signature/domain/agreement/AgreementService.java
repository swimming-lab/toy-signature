package swm.toy.signature.domain.agreement;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.agreement.item.AgreementItemService;
import swm.toy.signature.domain.item.ItemFindService;
import swm.toy.signature.domain.user.UserFindService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.util.Optionals.mapIfAllPresent;

@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementItemService agreementItemService;
    private final UserFindService userFindService;
    private final ItemFindService itemFindService;

    AgreementService(
            AgreementRepository agreementRepository,
            AgreementItemService agreementItemService,
            UserFindService userFindService,
            ItemFindService itemFindService) {
        this.agreementRepository = agreementRepository;
        this.agreementItemService = agreementItemService;
        this.userFindService = userFindService;
        this.itemFindService = itemFindService;
    }

    @Transactional
    public Agreement createAgreement(long authorId, AgreementCreateRequest request) {
        List<AgreementItem> items = request.getItemIds().stream()
                .map(id -> AgreementItem.of(
                        itemFindService.getItemByIdAndStatus(id).orElseThrow(NoSuchElementException::new)))
                .collect(Collectors.toList());

        return userFindService
                .findById(authorId)
                .map(author -> author.createAgreement(request.getAgreementContents()))
                .map(agreement -> {
                    items.forEach(agreement::addAgreementItems);
                    return agreement;
                })
                .map(agreementRepository::save)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Agreement updateAgreement(long userId, AgreementUpdateRequest request) {
        return mapIfAllPresent(
                        userFindService.findById(userId),
                        getItemById(request.getAgreementId()),
                        (user, agreement) -> user.updateAgreement(agreement, request))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Agreement updateAgreementStatus(long userId, AgreementUpdateRequest request) {
        return mapIfAllPresent(
                userFindService.findById(userId),
                getItemById(request.getAgreementId()),
                (user, agreement) -> agreement.updateStatus(user, request))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Optional<Agreement> getItemById(long agreementId) {
        return agreementRepository.findById(agreementId);
    }

    @Transactional(readOnly = true)
    public List<Agreement> getAgreementByAuthorId(long authorId, Pageable pageable) {
        return agreementRepository.findAllByAuthorId(authorId, pageable);
    }
}
