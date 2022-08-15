package swm.toy.signature.application.item;

import javax.validation.constraints.AssertTrue;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipQueryParam {
    protected Integer offset = 0;
    protected Integer limit = 30;

    @AssertTrue
    protected boolean getValidPage() {
        return (offset != null && limit != null) || (offset == null && limit == null);
    }
}
