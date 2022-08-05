package swm.toy.signature.domain.equip.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipTypeDto {
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String type;

    private String heavy;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single<T> {
        private T equipType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Multiple {
        private List<EquipTypeDto> equipTypes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotNull
        private Long id;
        private String type;
        private String heavy;
    }
}
