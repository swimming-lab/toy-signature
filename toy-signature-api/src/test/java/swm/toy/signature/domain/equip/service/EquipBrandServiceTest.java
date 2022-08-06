package swm.toy.signature.domain.equip.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swm.toy.signature.domain.equip.dto.EquipBrandDto;
import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
import swm.toy.signature.domain.user.dto.UserDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipBrandServiceTest {
    EquipBrandServiceImpl equipBrandService;

    @Mock
    EquipBrandRepository equipBrandRepository;

    UserDto.Auth authUser;

    private EquipBrandDto equipBrand;
    private EquipBrandEntity expectedEquipBrand;

    @BeforeEach
    void setUp() {
        equipBrandService = new EquipBrandServiceImpl(equipBrandRepository);

        authUser = UserDto.Auth.builder()
                .id(1L)
                .email("email@email.com")
                .name("testUser")
                .bio("bio")
                .image("photo-path")
                .build();

        equipBrand = EquipBrandDto.builder()
                .brandName("닛산")
                .build();

        expectedEquipBrand = EquipBrandEntity.builder()
                .brandName(equipBrand.getBrandName())
                .build();
    }

    @Test
    void whenValidEquipBrandForm_thenReturnEquipBrand() {
        when(equipBrandRepository.save(any(EquipBrandEntity.class))).thenReturn(expectedEquipBrand);

        EquipBrandDto actual = equipBrandService.createEquipBrand(equipBrand, authUser);
        assertNotNull(actual);
        assertEquals(expectedEquipBrand.getBrandName(), actual.getBrandName());
    }

    @Test
    void whenUpdateEquipBrandWithNewBrandName_thenReturnUpdatedSingleEqiupBrandWithUpdatedBrandNamew() {
//        EquipBrandDto.Update update = EquipBrandDto.Update.builder().brandName("혼다").build();
//
//        when(equipBrandRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(expectedEquipBrand));
//
//        EquipBrandDto actual = equipBrandService.updateEquipBrand(update, authUser);
//        System.out.println(actual);
//
//        assertThat(actual).isNotNull();
//        assertThat(expectedEquipBrand.getBrandName()).isEqualTo(actual.getBrandName());
//        assertEquals(update.getBrandName(), actual.getBrandName());
    }
}