package br.com.zup.MercadoLivre.question;

import br.com.zup.MercadoLivre.user.UserDTO;
import java.time.LocalDate;

public class QuestionResponseDTO {
    private String title;
    private LocalDate createdAt;
    private UserDTO user;

    @Deprecated
    public QuestionResponseDTO() {}

    public QuestionResponseDTO(String title, LocalDate createdAt, UserDTO user) {
        this.title = title;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public UserDTO getUser() {
        return user;
    }
}
