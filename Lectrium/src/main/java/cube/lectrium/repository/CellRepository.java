package cube.lectrium.repository;

import cube.lectrium.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
    Cell getCellByTitle(String title);
}
