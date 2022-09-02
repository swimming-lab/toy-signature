package swm.toy.signature.application.user;

import lombok.Value;
import swm.toy.signature.domain.user.Profile;

import static java.lang.String.valueOf;

@Value
public class ProfileModel {

    ProfileModelNested profile;

    public static ProfileModel from(Profile profile) {
        return new ProfileModel(ProfileModelNested.from(profile));
    }

    @Value
    public static class ProfileModelNested {
        String username;
        String image;
        boolean following;

        public static ProfileModelNested from(Profile profile) {
            return new ProfileModelNested(
                    valueOf(profile.getUserName()), valueOf(profile.getImage()), profile.isFollowing());
        }
    }
}
