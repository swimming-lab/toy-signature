package swm.toy.signature.domain.item.brand;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemBrandService implements ItemBrandFindService {

    private final ItemBrandRepository itemBrandRepository;

    ItemBrandService(ItemBrandRepository itemBrandRepository) {
        this.itemBrandRepository = itemBrandRepository;
    }

    @Transactional
    public ItemBrand createItemBrand(String brandName) {
        return itemBrandRepository.save(ItemBrand.of(brandName));
    }

    @Transactional
    public ItemBrand updateItemBrand(Long itemBrandId, String brandName) {
        final var itemBrand =
                itemBrandRepository.findById(itemBrandId).orElseThrow(NoSuchElementException::new);
        itemBrand.changeBrandName(brandName);
        return itemBrandRepository.save(itemBrand);
    }

    @Transactional(readOnly = true)
    public Optional<ItemBrand> findByBrandName(String brandName) {
        return itemBrandRepository.findFirstByBrandName(brandName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemBrand> findAll() {
        return itemBrandRepository.findAll(Sort.by(Sort.Direction.ASC, "brandName"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemBrand> findById(long id) {
        return itemBrandRepository.findById(id);
    }
}
