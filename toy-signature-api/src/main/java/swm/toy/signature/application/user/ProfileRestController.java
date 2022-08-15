package swm.toy.signature.application.user;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static swm.toy.signature.application.user.ProfileModel.fromProfile;

import java.util.NoSuchElementException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.jwt.JWTPayload;
import swm.toy.signature.domain.user.ProfileService;
import swm.toy.signature.domain.user.UserName;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RequestMapping("/profiles")
@RestController
class ProfileRestController {

    private final ProfileService profileService;

    ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{username}")
    public ProfileModel getProfileByUsername(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @PathVariable UserName username) {
        return ofNullable(jwtPayload)
                .map(JWTPayload::getUserId)
                .map(viewerId -> profileService.viewProfile(viewerId, username))
                .map(ProfileModel::fromProfile)
                .orElseGet(() -> fromProfile(profileService.viewProfile(username)));
    }

    @PostMapping("/{username}/follow")
    public ProfileModel followUser(
            @AuthenticationPrincipal UserJWTPayload followerPayload,
            @PathVariable UserName username) {
        return fromProfile(
                profileService.followAndViewProfile(followerPayload.getUserId(), username));
    }

    @DeleteMapping("/{username}/follow")
    public ProfileModel unfollowUser(
            @AuthenticationPrincipal UserJWTPayload followerPayload,
            @PathVariable UserName username) {
        return fromProfile(
                profileService.unfollowAndViewProfile(followerPayload.getUserId(), username));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    void handleNoSuchElementException(NoSuchElementException exception) {
        // return NOT FOUND status
    }
}
