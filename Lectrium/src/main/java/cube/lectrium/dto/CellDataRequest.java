package cube.lectrium.dto;

import cube.lectrium.model.Cell;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CellDataRequest {

    // Все поля кроме Title для JSON сериализации

    @NotBlank(message = "Content не может быть пустым")
    @Size(max = 500, message = "Content должен быть не длиннее 500 символов")
    private String content;

    @NotNull(message = "CellType не может быть null")
    private Cell.CellType cellType;

}
