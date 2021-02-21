package br.com.zup.MercadoLivre.user;

import br.com.zup.MercadoLivre.annotation.Singular;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserDTO {

    @NotBlank
    @Email
    @Singular(domainClass = User.class, fieldName = "login")
    private final String login;

    @NotBlank
    @Size(min = 6)
    private final String password;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private final LocalDate createdAt;

    public UserDTO(
        String login,
        String password,
        LocalDate createdAt
    ) {
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
    }

    public User toModel() {
        return new User(
            login,
            new BCryptPasswordEncoder().encode(password),
            createdAt
        );
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
