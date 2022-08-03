package swm.toy.signature.domain.equip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EquipDto {
    private Integer equipCode;
    private String equipModel;
    private String equipName;
    private Integer seq;
    private String licensePlate;
    private String insuranceYn;
    private String routineYn;
    private Author author;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        @JsonProperty("username")
        private String name;
        private String bio;
        private String image;
        private Boolean following;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleEquip<T> {
        private T equip;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleEquip {
        private List<EquipDto> equips;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String title;
        private String description;
        private String body;
    }
}
