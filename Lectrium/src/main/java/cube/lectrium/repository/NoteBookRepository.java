package cube.lectrium.repository;

import cube.lectrium.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {
    Optional<NoteBook> findByTitle(String title);
}
