package swm.toy.signature.base.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String username) {
        UserDetails userDetail = userDetailsService.loadUserByUsername(username);
        if (userDetail == null) return null;
        UserEntity userEntity = (UserEntity) userDetail;
        UserDto.Auth authenticatedUser = UserDto.Auth.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .bio(userEntity.getBio())
                .image(userEntity.getImage())
                .build();
        return new UsernamePasswordAuthenticationToken(authenticatedUser, "", userDetail.getAuthorities());
    }
}
