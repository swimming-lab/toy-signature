package swm.toy.signature.domain.item.type;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
    Optional<ItemType> findByType(String type);
}
