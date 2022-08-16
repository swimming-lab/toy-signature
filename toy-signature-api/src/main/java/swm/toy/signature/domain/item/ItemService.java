package swm.toy.signature.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.domain.item.brand.ItemBrandRepository;
import swm.toy.signature.domain.item.type.ItemTypeRepository;
import swm.toy.signature.domain.user.UserFindService;
import swm.toy.signature.infrastructure.exception.AppException;
import swm.toy.signature.infrastructure.exception.ErrorCode;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.util.Optionals.mapIfAllPresent;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final ItemBrandRepository itemBrandRepository;
    private final UserFindService userFindService;

    ItemService(
            ItemRepository itemRepository,
            ItemTypeRepository itemTypeRepository,
            ItemBrandRepository itemBrandRepository,
            UserFindService userFindService) {
        this.itemRepository = itemRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.itemBrandRepository = itemBrandRepository;
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
                itemTypeRepository
                        .findById(request.getItemTypeId())
                        .orElseThrow(NoSuchElementException::new);
        contents.setItemType(itemType);

        final var itemBrand =
                itemBrandRepository
                        .findById(request.getItemBrandId())
                        .orElseThrow(NoSuchElementException::new);
        contents.setItemBrand(itemBrand);

        //        itemBrandRepository.findById(equip.getEquipBrand().getId())
        //                .orElseThrow(() -> new AppException(Error.EQUIP_BRAND_NOT_FOUND));

        return userFindService
                .findById(authorId)
                .map(author -> author.createItem(contents))
                .map(itemRepository::save)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Item updateItem(long userId, long itemId, ItemUpdateRequest request) {
        return mapIfAllPresent(
                        userFindService.findById(userId),
                        getItemById(itemId),
                        (user, item) -> user.updateItem(item, request))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Optional<Item> getItemById(long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional(readOnly = true)
    public Page<Item> getItemsByAuthorId(long authorId, Pageable pageable) {
        return itemRepository.findAllByAuthorId(authorId, pageable);
    }

    //    @Transactional(readOnly = true)
    //    public List<EquipDto> getEquips(EquipQueryParam equipQueryParam, UserDto.Auth authUser) {
    //        Pageable pageable = null;
    //        if (equipQueryParam.getOffset() != null) {
    //            pageable = PageRequest.of(equipQueryParam.getOffset(),
    // equipQueryParam.getLimit());
    //        }
    //
    //        return itemRepository.findByAuthorIdOrderBySequenceAsc(authUser.getId(),
    // pageable).stream().map(entity -> {
    //            return convertEntityToDto(entity);
    //        }).collect(Collectors.toList());
    //    }
}
