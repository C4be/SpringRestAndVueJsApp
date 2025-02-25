package cube.lectrium.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "material", name = "notebook")
public class NoteBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cell> cells;
}
