package swm.toy.signature.domain.user;

public class UserSignUpRequest {

    private final Email email;
    private final UserName userName;
    private final String rawPassword;
    private final Phone phone;

    public UserSignUpRequest(Email email, UserName userName, String rawPassword, Phone phone) {
        this.email = email;
        this.userName = userName;
        this.rawPassword = rawPassword;
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public Phone getPhone() {
        return phone;
    }
}
