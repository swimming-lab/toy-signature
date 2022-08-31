package swm.toy.signature.application.user;

import static swm.toy.signature.application.user.UserModel.fromUserAndToken;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.jwt.JWTSerializer;
import swm.toy.signature.domain.user.Email;
import swm.toy.signature.domain.user.UserService;
import swm.toy.signature.infrastructure.jwt.UserJWTPayload;

@RestController
class UserRestController {

    private final JWTSerializer jwtSerializer;
    private final UserService userService;

    UserRestController(UserService userService, JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.jwtSerializer = jwtSerializer;
    }

    @PostMapping("/users")
    public ResponseEntity postUser(@Valid @RequestBody UserPostParam param) {
        final var userSaved = userService.signUp(param.toSignUpRequest());
        return ResponseEntity.ok(fromUserAndToken(userSaved, jwtSerializer.jwtFromUser(userSaved)));
    }

    @PostMapping("/users/login")
    public ResponseEntity loginUser(@Valid @RequestBody UserLoginParam param) {
        return ResponseEntity.ok(userService
                .login(new Email(param.getEmail()), param.getPassword())
                .map(user -> fromUserAndToken(user, jwtSerializer.jwtFromUser(user))));
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@AuthenticationPrincipal UserJWTPayload jwtPayload) {
        return ResponseEntity.ok(userService
                .findById(jwtPayload.getUserId())
                .map(user -> fromUserAndToken(user, getCurrentCredential())));
    }

    @PutMapping("/user")
    public ResponseEntity putUser(
            @AuthenticationPrincipal UserJWTPayload jwtPayload, @Valid @RequestBody UserPutParam param) {
        final var userUpdated = userService.updateUser(jwtPayload.getUserId(), param.toUpdateRequest());
        return ResponseEntity.ok(fromUserAndToken(userUpdated, getCurrentCredential()));
    }

    private static String getCurrentCredential() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getCredentials()
                .toString();
    }
}
