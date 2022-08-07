package swm.toy.signature.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.base.exception.AppException;
import swm.toy.signature.base.exception.Error;
import swm.toy.signature.base.security.JwtUtils;
import swm.toy.signature.domain.user.dto.UserDto;
import swm.toy.signature.domain.user.entity.UserEntity;
import swm.toy.signature.domain.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registration(final UserDto.Registration registration) {
        userRepository.findByNameOrEmail(registration.getName(), registration.getEmail())
                .stream().findAny().ifPresent(entity -> {throw new AppException(Error.DUPLICATED_USER);});
        UserEntity userEntity = UserEntity.builder()
                .name(registration.getName())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        userRepository.save(userEntity);
        return convertEntityToDto(userEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto login(UserDto.Login login) {
        UserEntity userEntity = userRepository.findByEmail(login.getEmail())
                .filter(user -> passwordEncoder.matches(login.getPassword(), user.getPassword())).orElseThrow(() -> new AppException(Error.LOGIN_INFO_INVALID));
        return convertEntityToDto(userEntity);
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .token(jwtUtils.encode(userEntity.getUsername()))
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto currentUser(UserDto.Auth authUser) {
        UserEntity userEntity = userRepository.findById(authUser.getId()).orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
        return convertEntityToDto(userEntity);
    }

    @Override
    public UserDto update(UserDto.Update update, UserDto.Auth authUser) {
        UserEntity userEntity = userRepository.findById(authUser.getId()).orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        if (update.getName() != null) {
            userRepository.findByName(update.getName())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {throw new AppException(Error.DUPLICATED_USER);});
            userEntity.setName(update.getName());
        }

        if (update.getEmail() != null) {
            userRepository.findByEmail(update.getEmail())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {throw new AppException(Error.DUPLICATED_USER);});
            userEntity.setEmail(update.getEmail());
        }

        if (update.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(update.getPassword()));
        }

        userRepository.save(userEntity);
        return convertEntityToDto(userEntity);
    }
}
