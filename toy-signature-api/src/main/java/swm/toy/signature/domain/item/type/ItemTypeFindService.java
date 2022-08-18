package swm.toy.signature.domain.item.type;

import java.util.List;
import java.util.Optional;

public interface ItemTypeFindService {

    Optional<ItemType> findById(long id);

    List<ItemType> findAll();
}
