package swm.toy.signature.user;

import swm.toy.signature.domain.user.UserName;

public class ProfileTestUtils {

    private ProfileTestUtils() {}

    public static Profile profileOf(UserName userName, Image image, boolean following) {
        final var profile = new Profile(userName);
        profile.changeImage(image);
        return profile.withFollowing(following);
    }
}
