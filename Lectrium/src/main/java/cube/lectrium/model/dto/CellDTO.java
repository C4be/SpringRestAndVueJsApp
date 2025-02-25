package cube.lectrium.model.dto;

import cube.lectrium.model.Cell.CellType;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@Builder
public class CellDTO {

    @NotBlank(message = "Title у CELL не может быть пустым!")
    @Size(max = 100, message = "Title у CELL должен быть не длиннее 100 символов!")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 .,!?]+$", message = "Title у CELL содержит недопустимые символы!")
    private String title;

    @NotBlank(message = "Content у CELL не может быть пустым!")
    @Size(max = 2000, message = "Content у CELL должен быть не длиннее 2000 символов!")
    private String content;

    @NotNull(message = "CellType у CELL не может быть null!")
    private CellType cellType;

    @Min(value = 0, message = "NumberInSequence у CELL не может быть отрицательным!")
    @Max(value = Integer.MAX_VALUE, message = "NumberInSequence у CELL больше максимального: " + Integer.MAX_VALUE + "!")
    private int numberInSequence;

    @NotNull(message = "Notebook ID у CELL не может быть null!")
    private Long notebookId;

}
