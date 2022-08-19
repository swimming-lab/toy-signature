package swm.toy.signature.domain.item;

import swm.toy.signature.infrastructure.converter.CodeValueConverter;

public class StatusConverter extends CodeValueConverter<Status> {
    StatusConverter() {
        super(Status.class);
    }
}
