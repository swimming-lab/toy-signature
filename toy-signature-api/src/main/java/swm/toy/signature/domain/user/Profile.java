package swm.toy.signature.domain.user;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
public class Profile {

    @Embedded
    private UserName userName;

    @Embedded
    private Image image;

    @Transient
    private boolean following;

    public Profile(UserName userName) {
        this(userName, null, false);
    }

    private Profile(UserName userName, Image image, boolean following) {
        this.userName = userName;
        this.image = image;
        this.following = following;
    }

    protected Profile() {}

    public UserName getUserName() {
        return userName;
    }

    public Image getImage() {
        return image;
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
}
