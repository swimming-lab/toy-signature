package swm.toy.signature.domain.equip.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaAuditing
class EquipBrandRepositoryTest {
    @Autowired
    EquipBrandRepository equipBrandRepository;

    private EquipBrandEntity savedEquipBrand;

    @BeforeEach
    void setUp() {
        savedEquipBrand = EquipBrandEntity.builder()
                .brandName("닛산")
                .build();

        savedEquipBrand = equipBrandRepository.save(savedEquipBrand);
    }

    @Test
    void whenSave_thenCanBeFound() {
        Long id = savedEquipBrand.getId();

        Optional<EquipBrandEntity> equipBrand = equipBrandRepository.findById(id);

        assertTrue(equipBrand.isPresent());
    }
}