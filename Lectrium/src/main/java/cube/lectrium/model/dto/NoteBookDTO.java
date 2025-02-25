package cube.lectrium.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Builder
public class NoteBookDTO {

    @NotBlank(message = "Title у NOTEBOOK не может быть пустым!")
    @Size(max = 100, message = "Title у NOTEBOOK должен быть не длиннее 100 символов!")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 .,!?]+$", message = "Title у NOTEBOOK содержит недопустимые символы!")
    private String title;

    private Long topicId;

    private List<CellDTO> cells;
}

