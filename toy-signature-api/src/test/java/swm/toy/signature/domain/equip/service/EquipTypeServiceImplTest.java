package swm.toy.signature.domain.equip.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.equip.dto.EquipTypeDto;
import swm.toy.signature.domain.equip.entity.EquipTypeEntity;
import swm.toy.signature.domain.equip.repository.EquipTypeRepository;
import swm.toy.signature.domain.user.dto.UserDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipTypeServiceImplTest {
    EquipTypeServiceImpl equipTypeService;

    @Mock
    EquipTypeRepository equipTypeRepository;

    UserDto.Auth authUser;

    private EquipTypeDto equipType;
    private EquipTypeEntity expectedEquipType;

    @BeforeEach
    void setUp() {
        equipTypeService = new EquipTypeServiceImpl(equipTypeRepository);

        authUser = UserDto.Auth.builder()
                .id(1L)
                .email("email@email.com")
                .name("testUser")
                .build();

        equipType = EquipTypeDto.builder()
                .type("포크레인")
                .heavy("1.3톤")
                .build();

        expectedEquipType = EquipTypeEntity.builder()
                .type(equipType.getType())
                .heavy(equipType.getHeavy())
                .build();
    }

    @Test
    void whenValidEquipTypeForm_thenReturnEquipType() {
        when(equipTypeRepository.save(any(EquipTypeEntity.class))).thenReturn(expectedEquipType);

        EquipTypeDto actual = equipTypeService.createEquipType(equipType, authUser);
        assertNotNull(actual);
        assertEquals(expectedEquipType.getType(), actual.getType());
        assertEquals(expectedEquipType.getHeavy(), actual.getHeavy());
    }

    @Test
    void whenUpdateEquipTypeDto_thenReturnUpdatedEquipType() {
        EquipTypeDto.Update update = EquipTypeDto.Update.builder()
                .id(1L)
                .heavy("1.5톤")
                .build();

        when(equipTypeRepository.findById(eq(update.getId()))).thenReturn(Optional.of(expectedEquipType));

        EquipTypeDto actual = equipTypeService.updateEquipType(update, authUser);
        System.out.println(actual);

        assertNotNull(actual.getHeavy());
        assertEquals(update.getHeavy(), actual.getHeavy());
    }
}