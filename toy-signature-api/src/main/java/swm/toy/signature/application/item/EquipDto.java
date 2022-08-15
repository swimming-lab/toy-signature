package swm.toy.signature.application.item;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipDto {
    private Long id;

    @NotNull private String licensePlate;

    @NotNull private Integer sequence;

    @NotNull private String insuranceYn;

    @NotNull private String routineYn;

    @NotNull private EquipTypeDto equipType;

    @NotNull private EquipBrandDto equipBrand;

    private Author author;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private Long id;
        private String email;
        private String name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single<T> {
        private T equip;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Multiple {
        private List<EquipDto> equips;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotNull private Long id;
        private String licensePlate;
        private Integer sequence;
        private String insuranceYn;
        private String routineYn;
        private EquipTypeDto.Update equipType;
        private EquipBrandDto.Update equipBrand;
    }
}
