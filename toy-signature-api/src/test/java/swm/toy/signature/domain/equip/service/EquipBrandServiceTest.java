//package swm.toy.signature.domain.equip.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import swm.toy.signature.domain.equip.dto.EquipBrandDto;
//import swm.toy.signature.domain.equip.entity.EquipBrandEntity;
//import swm.toy.signature.domain.equip.repository.EquipBrandRepository;
//
//@ExtendWith(MockitoExtension.class)
//class EquipBrandServiceTest {
//    @Mock
//    EquipBrandService equipBrandService;
//
//    @Mock
//    EquipBrandRepository equipBrandRepository;
//
//    private EquipBrandDto equipBrand;
//    private EquipBrandEntity expectedEquipBrand;
//
//    @BeforeEach
//    void setUp() {
//        equipBrand = EquipBrandDto.builder()
//                .brandName("닛산")
//                .build();
//
//        expectedEquipBrand = EquipBrandEntity.builder()
//                .brandName(equipBrand.getBrandName())
//                .build();
//    }
//
////    @Test
////    void whenValidEquipBrand
//}