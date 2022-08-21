package swm.toy.signature.domain.item;

import java.util.Optional;

public interface ItemFindService {

    public Optional<Item> getItemByIdAndStatus(long itemId);
}
