package swm.toy.signature.domain.agreement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.agreement.item.AgreementItem;
import swm.toy.signature.domain.agreement.item.AgreementItemService;
import swm.toy.signature.domain.item.ItemFindService;
import swm.toy.signature.domain.user.UserFindService;

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
        List<AgreementItem> items =
                request.getItemIds().stream()
                        .map(
                                id ->
                                        AgreementItem.of(
                                                itemFindService
                                                        .getItemByIdAndStatus(id)
                                                        .orElseThrow(NoSuchElementException::new)))
                        .collect(Collectors.toList());

        return userFindService
                .findById(authorId)
                .map(author -> author.createAgreement(request.getAgreementContents()))
                .map(
                        agreement -> {
                            items.forEach(agreement::addAgreementItems);
                            return agreement;
                        })
                .map(agreementRepository::save)
                .orElseThrow(NoSuchElementException::new);
    }
    //
    //    @Transactional
    //    public Item updateItem(long userId, ItemUpdateRequest request) {
    //        request.getItemTypeIdToUpdate()
    //                .ifPresent(
    //                        id -> {
    //                            itemTypeFindService
    //                                    .findById(id)
    //                                    .ifPresent(request::setItemTypeToUpdate);
    //                        });
    //        request.getItemBrandIdToUpdate()
    //                .ifPresent(
    //                        id -> {
    //                            itemBrandFindService
    //                                    .findById(id)
    //                                    .ifPresent(request::setItemBrandToUpdate);
    //                        });
    //
    //        return mapIfAllPresent(
    //                        userFindService.findById(userId),
    //                        getItemById(request.getItemId()),
    //                        (user, item) -> user.updateItem(item, request))
    //                .orElseThrow(NoSuchElementException::new);
    //    }
    //
    //    @Transactional(readOnly = true)
    //    public Optional<Item> getItemById(long itemId) {
    //        return itemRepository.findById(itemId);
    //    }
    //
    //    @Transactional(readOnly = true)
    //    public Page<Item> getItemsByAuthorId(long authorId, Pageable pageable) {
    //        return itemRepository.findAllByAuthorIdOrderByContentsSequenceAsc(authorId, pageable);
    //    }
}
