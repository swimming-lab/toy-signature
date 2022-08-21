package swm.toy.signature.domain.user;

import swm.toy.signature.infrastructure.converter.CodeValueConverter;

public class UserStatusConverter extends CodeValueConverter<UserStatus> {
    UserStatusConverter() {
        super(UserStatus.class);
    }
}
