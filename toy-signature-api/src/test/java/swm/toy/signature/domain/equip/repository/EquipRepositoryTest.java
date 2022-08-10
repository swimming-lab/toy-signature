package swm.toy.signature.domain.equip.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.entity.EquipEntity;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.user.entity.UserEntity;
import swm.toy.signature.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaAuditing
class EquipRepositoryTest {
    @Autowired
    EquipRepository equipRepository;
    @Autowired
    EquipTypeRepository equipTypeRepository;
    @Autowired
    EquipBrandRepository equipBrandRepository;
    @Autowired
    UserRepository userRepository;

    private EquipTypeEntity equipType;
    private EquipBrandEntity equipBrand;
    private EquipEntity equip1;
    private UserEntity user1;

    @BeforeEach
    void setUp() {
        user1 = UserEntity.builder()
                .name("username")
                .email("test@test.com")
                .password("password")
                .build();
        userRepository.save(user1);

        equipType = EquipTypeEntity.builder()
                .type("포크레인")
                .heavy("5톤")
                .build();
        equipTypeRepository.save(equipType);

        equipBrand = EquipBrandEntity.builder()
                .brandName("닛산")
                .build();
        equipBrandRepository.save(equipBrand);

        equip1 = EquipEntity.builder()
                .licensePlate("62주7416")
                .sequence(1)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipBrand(equipBrand)
                .equipType(equipType)
                .author(user1)
                .build();
        equip1 = equipRepository.save(equip1);
    }

    @Test
    void whenListOfAuthorId_thenReturnEquipsHaveAuthorIdAndOrderBySequenceAtAsc() {
        EquipEntity equip2 = EquipEntity.builder()
                .licensePlate("12버1234")
                .sequence(1)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipBrand(equipBrand)
                .equipType(equipType)
                .author(user1)
                .build();

        EquipEntity equip3 = EquipEntity.builder()
                .licensePlate("98마2223")
                .sequence(2)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipBrand(equipBrand)
                .equipType(equipType)
                .author(user1)
                .build();

        equipRepository.saveAll(List.of(equip2, equip3));

        List<EquipEntity> actual = equipRepository.findByAuthorIdOrderBySequenceAsc(user1.getId(), PageRequest.of(0, 3));

        assertEquals(3, actual.size());
        assertEquals(actual.get(0).getSequence(), 1);
        assertEquals(actual.get(1).getSequence(), 1);
        assertEquals(actual.get(2).getSequence(), 2);
    }
}