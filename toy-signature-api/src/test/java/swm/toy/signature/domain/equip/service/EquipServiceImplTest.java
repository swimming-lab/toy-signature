package swm.toy.signature.domain.equip.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.equip.dto.EquipDto;
import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.entity.EquipEntity;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.equip.model.EquipQueryParam;
import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
import swm.toy.signature.domain.equip.repository.EquipRepository;
import swm.toy.signature.domain.equip.repository.EquipTypeRepository;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;
import swm.toy.signature.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipServiceImplTest {
    EquipServiceImpl equipService;

    @Mock
    EquipRepository equipRepository;

    @Mock
    EquipTypeRepository equipTypeRepository;

    @Mock
    EquipBrandRepository equipBrandRepository;

    @Mock
    UserRepository userRepository;

    private UserDto.Auth authUser;
    private EquipDto equip;
    private EquipTypeDto equipType;
    private EquipBrandDto equipBrand;
    private UserEntity userEntity;
    private EquipTypeEntity equipTypeEnttiy;
    private EquipBrandEntity equipBrandEntity;
    private EquipEntity expectedEquip;

    @BeforeEach
    void setUp() {
        equipService = new EquipServiceImpl(equipRepository, equipTypeRepository, equipBrandRepository);

        authUser = UserDto.Auth.builder()
                .id(1L)
                .email("email@email.com")
                .name("testUser")
                .build();

        equipType = EquipTypeDto.builder()
                .id(1L)
                .type("포크레인")
                .heavy("1.3톤")
                .build();

        equipBrand = EquipBrandDto.builder()
                .id(1L)
                .brandName("닛산")
                .build();

        userEntity = UserEntity.builder()
                .email("email@email.com")
                .name("testUser")
                .password("password")
                .build();

        equipTypeEnttiy = EquipTypeEntity.builder()
                .type("포크레인")
                .heavy("1.3톤")
                .build();

        equipBrandEntity = EquipBrandEntity.builder()
                .brandName("닛산")
                .build();

        expectedEquip = EquipEntity.builder()
                .licensePlate("62주7416")
                .sequence(1)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipType(equipTypeEnttiy)
                .equipBrand(equipBrandEntity)
                .author(userEntity)
                .build();
    }

    @Test
    void whenValidEquipForm_thenReturnEquip() {
        equip = EquipDto.builder()
                .licensePlate("62주7416")
                .sequence(1)
                .insuranceYn("Y")
                .routineYn("Y")
                .equipType(equipType)
                .equipBrand(equipBrand)
                .build();

        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(equipTypeRepository.findById(anyLong())).thenReturn(Optional.of(equipTypeEnttiy));
        when(equipBrandRepository.findById(anyLong())).thenReturn(Optional.of(equipBrandEntity));
        when(equipRepository.save(any(EquipEntity.class))).thenReturn(expectedEquip);

        EquipDto actual = equipService.createEquip(equip, authUser);
        assertNotNull(actual);
        assertEquals(expectedEquip.getLicensePlate(), actual.getLicensePlate());
        assertEquals(expectedEquip.getSequence(), actual.getSequence());
        assertEquals(expectedEquip.getInsuranceYn(), actual.getInsuranceYn());
        assertEquals(expectedEquip.getRoutineYn(), actual.getRoutineYn());
        assertEquals(expectedEquip.getEquipType().getType(), actual.getEquipType().getType());
        assertEquals(expectedEquip.getEquipBrand().getBrandName(), actual.getEquipBrand().getBrandName());
        assertEquals(expectedEquip.getAuthor().getName(), actual.getAuthor().getName());
    }

    @Test
    void whenQueryEquip_thenReturnEquips() {
        EquipQueryParam query = new EquipQueryParam();

        when(equipRepository.findByAuthorIdOrderBySequenceAsc(eq(1L), any())).thenReturn(List.of(expectedEquip));

        List<EquipDto> actual = equipService.getEquips(query, authUser);

        assertTrue(actual.size() > 0);
    }
}