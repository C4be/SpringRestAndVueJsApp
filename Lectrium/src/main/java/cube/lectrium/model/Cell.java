package cube.lectrium.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(schema = "journal", name = "cell")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "Title не может быть пустым")
    @Size(max = 50, message = "Title должен быть не длиннее 50 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 .,!?]+$", message = "Title содержит недопустимые символы")
    private String title;

    @Column(unique = true, nullable = false, length = 500)
    @NotBlank(message = "Content не может быть пустым")
    @Size(max = 500, message = "Content должен быть не длиннее 500 символов")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "CellType не может быть null")
    private CellType cellType;

    public enum CellType {
        PYTHON_CELL,
        JAVA_CELL,
        MARKDOWN_CELL,
        ROW_TEXT_CELL
    }

}
