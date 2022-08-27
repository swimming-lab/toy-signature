package swm.toy.signature.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
@EnableJpaAuditing
class UserRepositoryTest {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Test
    void when_save_user_expect_saved() {
        var userToSave = User.of(
                new Email("user@email.com"), new UserName("name"), Password.of("rawPassword", PASSWORD_ENCODER));

        User save = userRepository.save(userToSave);
        assertThat(save).hasNoNullFieldsOrProperties();
    }

    @Test
    void when_save_user_with_image_expect_saved() {
        var userToSave = User.of(
                new Email("user@email.com"), new UserName("name"), Password.of("rawPassword", PASSWORD_ENCODER));
        var imageToSave = new Image("some-image");

        userToSave.changeImage(imageToSave);

        assertThat(userRepository.save(userToSave)).extracting(User::getImage).isEqualTo(imageToSave);
    }
}
