package swm.toy.signature.domain.equipment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    @NotNull
    private Integer code;
    @NotNull
    private String model;
    @NotNull
    private String name;
    private Integer sequence;
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
        private List<EquipmentDto> equips;
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
