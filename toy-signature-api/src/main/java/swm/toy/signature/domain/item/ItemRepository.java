package swm.toy.signature.domain.item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(attributePaths = {"itemType", "itemBrand"})
    Optional<Item> findById(Long id);

    @EntityGraph(attributePaths = {"itemType", "itemBrand"})
    Optional<Item> findByIdAndStatus(Long id, Status status);

    Optional<Item> findFirstByContentsLicensePlate(String licensePlate);

    @EntityGraph(attributePaths = {"author", "itemType", "itemBrand"})
    List<Item> findAllByAuthorIdOrderByContentsSequenceAsc(long authorId, Pageable pageable);

    Long countByAuthorId(long authorId);

    Long countByAuthorIdAndStatus(long authorId, Status status);

    //    @EntityGraph("fetch-author-equipType-equipBrand")
    //    @Query("SELECT e FROM Item e WHERE e.author.id = :id ORDER BY e.sequence ASC")
    //    List<Item> findByAuthorIdOrderBySequenceAsc(@Param("id") Long id, Pageable pageable);
}
