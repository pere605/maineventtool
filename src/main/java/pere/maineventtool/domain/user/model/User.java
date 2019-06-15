package pere.maineventtool.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private UUID id;
    private String email;
    private String password;

    public User(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
