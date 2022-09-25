package swm.toy.signature.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
public class Profile {

    @Embedded
    private UserName userName;

    @Embedded
    private Image image;

    @Embedded
    private Phone phone;

    @Transient
    private boolean following;

    public Profile(UserName userName) {
        this(userName, null, false, new Phone("010-0000-0000"));
    }

    public Profile(UserName userName, Phone phone) {
        this(userName, null, false, phone);
    }

    private Profile(UserName userName, Image image, boolean following, Phone phone) {
        this.userName = userName;
        this.image = image;
        this.following = following;
        this.phone = phone;
    }

    protected Profile() {}

    public UserName getUserName() {
        return userName;
    }

    public Image getImage() {
        return image;
    }

    public Phone getPhone() {
        return phone;
    }

    public boolean isFollowing() {
        return following;
    }

    Profile withFollowing(boolean following) {
        this.following = following;
        return this;
    }

    void changeUserName(UserName userName) {
        this.userName = userName;
    }

    void changeImage(Image image) {
        this.image = image;
    }

    void changePhone(Phone phone) {
        this.phone = phone;
    }
}
