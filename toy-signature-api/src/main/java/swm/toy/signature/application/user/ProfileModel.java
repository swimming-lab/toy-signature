package swm.toy.signature.application.user;

import static java.lang.String.valueOf;

import lombok.Value;
import swm.toy.signature.domain.user.Profile;

@Value
public class ProfileModel {

    ProfileModelNested profile;

    public static ProfileModel fromProfile(Profile profile) {
        return new ProfileModel(ProfileModelNested.fromProfile(profile));
    }

    @Value
    public static class ProfileModelNested {
        String username;
        String image;
        boolean following;

        public static ProfileModelNested fromProfile(Profile profile) {
            return new ProfileModelNested(
                    valueOf(profile.getUserName()),
                    valueOf(profile.getImage()),
                    profile.isFollowing());
        }
    }
}
