package swm.toy.signature.domain.item.type;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemTypeService implements ItemTypeFindService {

    private final ItemTypeRepository itemTypeRepository;

    ItemTypeService(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Transactional
    public ItemType createItemType(String type, String heavy) {
        return itemTypeRepository.save(ItemType.of(type, heavy));
    }

    @Transactional
    public ItemType updateItemType(Long itemTypeId, String type, String heavy) {
        final var itemType =
                itemTypeRepository.findById(itemTypeId).orElseThrow(NoSuchElementException::new);
        if (itemType != null) {
            itemType.changeType(type);
        }

        if (heavy != null) {
            itemType.changeHeavy(heavy);
        }

        return itemTypeRepository.save(itemType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemType> findAll() {
        return itemTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "type", "heavy"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemType> findById(long id) {
        return itemTypeRepository.findById(id);
    }
}
