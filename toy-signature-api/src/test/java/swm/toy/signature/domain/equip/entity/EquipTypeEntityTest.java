package swm.toy.signature.domain.equip.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EquipTypeEntityTest {
    @Test
    void create() {
        //given

        //when
        EquipTypeEntity entity = EquipTypeEntity.builder()
                .type("포크레인")
                .heavy("5톤")
                .build();

        //then
        assertThat(entity.getType()).isEqualTo("포크레인");
        assertThat(entity.getHeavy()).isEqualTo("5톤");
    }

}