package swm.toy.signature.application.item;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipBrandDto {
    private Long id;

    private String brandName;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single<T> {
        private T equipBrand;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Multiple {
        private List<EquipBrandDto> equipBrands;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update<T> {
        @NotNull private Long id;
        @NotNull private String brandName;
    }
}
