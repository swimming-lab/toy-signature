package swm.toy.signature.domain.user;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import lombok.Builder;

@Builder
public class UserUpdateRequest {

    private final Email emailToUpdate;
    private final UserName userNameToUpdate;
    private final String passwordToUpdate;
    private final Image imageToUpdate;
    private final String statusToUpdate;
    private final Phone phoneToUpdate;

    Optional<Email> getEmailToUpdate() {
        return ofNullable(emailToUpdate);
    }

    Optional<UserName> getUserNameToUpdate() {
        return ofNullable(userNameToUpdate);
    }

    Optional<String> getPasswordToUpdate() {
        return ofNullable(passwordToUpdate);
    }

    Optional<Image> getImageToUpdate() {
        return ofNullable(imageToUpdate);
    }

    Optional<String> getStatusToUpdate() {
        return ofNullable(statusToUpdate);
    }

    Optional<Phone> getPhoneToUpdate() {
        return ofNullable(phoneToUpdate);
    }
}
