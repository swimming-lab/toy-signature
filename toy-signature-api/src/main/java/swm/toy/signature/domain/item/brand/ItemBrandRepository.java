package swm.toy.signature.domain.item.brand;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBrandRepository extends JpaRepository<ItemBrand, Long> {

    Optional<ItemBrand> findFirstByBrandName(String brandName);
}
