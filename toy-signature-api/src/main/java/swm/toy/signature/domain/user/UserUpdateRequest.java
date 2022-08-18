package swm.toy.signature.domain.user;

import lombok.Builder;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Builder
public class UserUpdateRequest {

    private final Email emailToUpdate;
    private final UserName userNameToUpdate;
    private final String passwordToUpdate;
    private final Image imageToUpdate;

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
}
