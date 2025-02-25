package cube.lectrium.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Builder
public class TopicDTO {
    @NotBlank(message = "Title у TOPIC не может быть пустым!")
    @Size(max = 100, message = "Title у TOPIC должен быть не длиннее 100 символов!")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 .,!?]+$", message = "Title у TOPIC содержит недопустимые символы!")
    private String title;

    @NotBlank(message = "Description у TOPIC не может быть пустым!")
    @Size(max = 1000, message = "Description у TOPIC должен быть не длиннее 1000 символов!")
    private String description;

    private List<NoteBookDTO> notebooks;
}
