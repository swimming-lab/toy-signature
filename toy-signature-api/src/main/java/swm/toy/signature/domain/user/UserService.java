package swm.toy.signature.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swm.toy.signature.infrastructure.exception.AppException;
import swm.toy.signature.infrastructure.exception.ErrorCode;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements UserFindService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User signUp(UserSignUpRequest request) {
        final var encodedPassword = Password.of(request.getRawPassword(), passwordEncoder);
        userRepository.findFirstByEmail(request.getEmail()).stream().findAny().ifPresent(entity -> {
            throw new AppException(ErrorCode.DUPLICATED_USER);
        });

        return userRepository.save(
                User.of(request.getEmail(), request.getUserName(), encodedPassword, Authority.of("ROLE_USER"), request.getPhone()));
    }

    @Transactional(readOnly = true)
    public Optional<User> login(Email email, String rawPassword) {
        return userRepository
                .findFirstByEmail(email)
                .filter(user -> user.matchesPassword(rawPassword, passwordEncoder));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(UserName userName) {
        return userRepository.findFirstByProfileUserName(userName);
    }

    @Transactional
    public User updateUser(long id, UserUpdateRequest request) {
        final var user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        request.getEmailToUpdate().ifPresent(user::changeEmail);
        request.getUserNameToUpdate().ifPresent(user::changeName);
        request.getPasswordToUpdate()
                .map(rawPassword -> Password.of(rawPassword, passwordEncoder))
                .ifPresent(user::changePassword);
        request.getImageToUpdate().ifPresent(user::changeImage);
        request.getPhoneToUpdate().ifPresent(user::changePhone);
        request.getStatusToUpdate()
                .map(statusToUpdate -> UserStatus.valueOf(statusToUpdate))
                .ifPresent(user::changeStatus);
        return userRepository.save(user);
    }
}
