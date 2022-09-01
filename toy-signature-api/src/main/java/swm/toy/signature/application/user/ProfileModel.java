package swm.toy.signature.application.user;

import lombok.Value;
import swm.toy.signature.domain.user.Profile;

import static java.lang.String.valueOf;

@Value
public class ProfileModel {

    String username;
    String image;
    boolean following;

    public static ProfileModel from(Profile profile) {
        return new ProfileModel(
                valueOf(profile.getUserName()), valueOf(profile.getImage()), profile.isFollowing());
    }
}
