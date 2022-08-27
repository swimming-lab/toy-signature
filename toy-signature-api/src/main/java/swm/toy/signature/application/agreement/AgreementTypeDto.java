package swm.toy.signature.application.agreement;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementTypeDto {
    private Long id;

    private String type;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single<T> {
        private T agreementType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Multiple {
        private List<AgreementTypeDto> agreementTypes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotNull
        private Long id;

        private String type;
    }
}
