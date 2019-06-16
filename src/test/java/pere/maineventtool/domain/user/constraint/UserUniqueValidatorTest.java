package pere.maineventtool.domain.user.constraint;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pere.maineventtool.domain.user.dto.UserRegistration;
import pere.maineventtool.domain.user.model.User;
import pere.maineventtool.domain.user.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class UserUniqueValidatorTest {

    @Autowired
    private UserRepository repository;
    @Autowired
    private Validator validator;

    @Test
    public void testValidateEmailUniqueness() {
        String email = "test@test.com";
        User existingUser = new User(UUID.randomUUID(), email, "password");
        repository.save(existingUser);

        UserRegistration newUser = new UserRegistration();
        newUser.setEmail(email);
        newUser.setPassword("password");
        Set<ConstraintViolation<UserRegistration>> violations = validator.validate(newUser);
        Assertions.assertEquals(1, violations.size());
    }
}
