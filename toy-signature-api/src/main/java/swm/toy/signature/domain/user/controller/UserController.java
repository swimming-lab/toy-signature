package swm.toy.signature.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto currentUser(@AuthenticationPrincipal UserDto.Auth authUser) {
        return userService.currentUser(authUser);
    }

    @PutMapping
    public UserDto update(@Valid @RequestBody UserDto.Update update, @AuthenticationPrincipal UserDto.Auth authUser) {
        return userService.update(update, authUser);
    }
}
