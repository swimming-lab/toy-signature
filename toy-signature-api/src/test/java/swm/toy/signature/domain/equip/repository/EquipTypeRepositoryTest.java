package swm.toy.signature.domain.equip.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaAuditing
class EquipTypeRepositoryTest {
    @Autowired
    EquipTypeRepository equipTypeRepository;

    private EquipTypeEntity savedEquipType;

    @BeforeEach
    void setUp() {
        savedEquipType = EquipTypeEntity.builder()
                .type("포크레인")
                .heavy("5톤")
                .build();

        savedEquipType = equipTypeRepository.save(savedEquipType);
    }

    @Test
    void whenSave_thenCanBeFound() {
        Long id = savedEquipType.getId();

        Optional<EquipTypeEntity> equipBrand = equipTypeRepository.findById(id);

        assertTrue(equipBrand.isPresent());
    }

    @Test
    void whenTypeExist_thenEquipTypeEntityFound() {
        String type = savedEquipType.getType();

        Optional<EquipTypeEntity> maybeEquipEntity = equipTypeRepository.findByType(type);

        assertTrue(maybeEquipEntity.isPresent());
    }
}