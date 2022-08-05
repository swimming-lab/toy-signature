package swm.toy.signature.domain.equip.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EquipBrandEntityTest {
    @Test
    void create() {
        //given

        //when
        EquipBrandEntity entity = EquipBrandEntity.builder()
                .brandName("토요타")
                .build();

        //then
        assertThat(entity.getBrandName()).isEqualTo("토요타");
    }
}