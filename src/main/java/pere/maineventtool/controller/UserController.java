package pere.maineventtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pere.maineventtool.domain.shared.validation.ValidationService;
import pere.maineventtool.domain.user.dto.UserRegistration;
import pere.maineventtool.domain.user.model.User;
import pere.maineventtool.domain.user.repository.UserRepository;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(
            @Valid @RequestBody UserRegistration registration,
            BindingResult validationResult
    ) {
        if (validationResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationService.parseResult(validationResult).getErrors());
        }

        User user = new User(
                UUID.randomUUID(),
                registration.getEmail(),
                this.passwordEncoder.encode(registration.getPassword())
        );

        userRepository.save(user);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/user/authenticate").build().toUri()).build();
    }
}
