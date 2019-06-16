package pere.maineventtool.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pere.maineventtool.domain.user.constraint.UniqueUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class UserRegistration {
    @NotBlank
    @Email
    @UniqueUser
    private String email;

    @NotBlank
    @Length(min = 8)
    private String password;
}
