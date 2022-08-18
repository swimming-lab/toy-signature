package swm.toy.signature.domain.item.brand;

import java.util.List;
import java.util.Optional;

public interface ItemBrandFindService {

    Optional<ItemBrand> findById(long id);

    List<ItemBrand> findAll();
}
