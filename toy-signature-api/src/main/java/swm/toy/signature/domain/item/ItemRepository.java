package swm.toy.signature.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findFirstByContentsLicensePlate(ItemContents itemContents);

    Page<Item> findAllByAuthorId(long authorId, Pageable pageable);

    //    @EntityGraph("fetch-author-equipType-equipBrand")
    //    @Query("SELECT e FROM Item e WHERE e.author.id = :id ORDER BY e.sequence ASC")
//    List<Item> findByAuthorIdOrderBySequenceAsc(@Param("id") Long id, Pageable pageable);
}
