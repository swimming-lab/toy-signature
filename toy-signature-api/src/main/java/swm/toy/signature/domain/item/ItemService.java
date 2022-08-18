package swm.toy.signature.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.item.brand.ItemBrandFindService;
import swm.toy.signature.domain.item.type.ItemTypeFindService;
import swm.toy.signature.domain.user.UserFindService;
import swm.toy.signature.infrastructure.exception.AppException;
import swm.toy.signature.infrastructure.exception.ErrorCode;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.util.Optionals.mapIfAllPresent;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemTypeFindService itemTypeFindService;
    private final ItemBrandFindService itemBrandFindService;
    private final UserFindService userFindService;

    ItemService(
            ItemRepository itemRepository,
            ItemTypeFindService itemTypeFindService,
            ItemBrandFindService itemBrandFindService,
            UserFindService userFindService) {
        this.itemRepository = itemRepository;
        this.itemTypeFindService = itemTypeFindService;
        this.itemBrandFindService = itemBrandFindService;
        this.userFindService = userFindService;
    }

    @Transactional
    public Item createItem(long authorId, ItemCreateRequest request) {
        ItemContents contents = request.getItemContents();
        itemRepository.findFirstByContentsLicensePlate(contents.getLicensePlate()).stream()
                .findAny()
                .ifPresent(
                        entity -> {
                            throw new AppException(ErrorCode.DUPLICATED_ITEM);
                        });

        final var itemType =
                itemTypeFindService
                        .findById(request.getItemTypeId())
                        .orElseThrow(NoSuchElementException::new);

        final var itemBrand =
                itemBrandFindService
                        .findById(request.getItemBrandId())
                        .orElseThrow(NoSuchElementException::new);

        return userFindService
                .findById(authorId)
                .map(author -> author.createItem(contents, itemType, itemBrand))
                .map(itemRepository::save)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Item updateItem(long userId, ItemUpdateRequest request) {
        request.getItemTypeIdToUpdate()
                .ifPresent(
                        id -> {
                            itemTypeFindService.findById(id).ifPresent(request::setItemTypeToUpdate);
                        });
        request.getItemBrandIdToUpdate()
                .ifPresent(
                        id -> {
                            itemBrandFindService
                                    .findById(id)
                                    .ifPresent(request::setItemBrandToUpdate);
                        });

        return mapIfAllPresent(
                        userFindService.findById(userId),
                        getItemById(request.getItemId()),
                        (user, item) -> user.updateItem(item, request))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Optional<Item> getItemById(long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional(readOnly = true)
    public Page<Item> getItemsByAuthorId(long authorId, Pageable pageable) {
        return itemRepository.findAllByAuthorIdOrderByContentsSequenceAsc(authorId, pageable);
        //        return itemRepository.findAllByAuthorIdOrderBySequenceAsc(authorId, pageable);
    }

//        @Transactional(readOnly = true)
//        public List<EquipDto> getEquips(EquipQueryParam equipQueryParam, UserDto.Auth authUser) {
//            Pageable pageable = null;
//            if (equipQueryParam.getOffset() != null) {
//                pageable = PageRequest.of(equipQueryParam.getOffset(),
//     equipQueryParam.getLimit());
//            }
//
//            return itemRepository.findByAuthorIdOrderBySequenceAsc(authUser.getId(),
//     pageable).stream().map(entity -> {
//                return convertEntityToDto(entity);
//            }).collect(Collectors.toList());
//        }
}
