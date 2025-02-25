package cube.lectrium.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "material", name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2_000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CellType cellType;

    @Column(nullable = false)
    private int numberInSequence;

    @ManyToOne
    @JoinColumn(name = "notebook_id", nullable = false)
    private NoteBook notebook;

    public enum CellType {
        PYTHON_CELL,
        JAVA_CELL,
        MARKDOWN_CELL,
        ROW_TEXT_CELL
    }
}
