package swm.toy.signature.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@JsonTypeName("errors")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class ErrorMessages {

    private final int status;
    private final List<String> body = new ArrayList<>();
    ;

    ErrorMessages(int status) {
        this.status = status;
    }

    public void append(String message) {
        body.add(message);
    }
}
