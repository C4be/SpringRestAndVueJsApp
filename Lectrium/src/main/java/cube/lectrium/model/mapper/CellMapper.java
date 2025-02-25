package cube.lectrium.model.mapper;

import cube.lectrium.model.Cell;
import cube.lectrium.model.NoteBook;
import cube.lectrium.model.dto.CellDTO;
import org.springframework.stereotype.Component;
import cube.lectrium.repository.NoteBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CellMapper {

    @Autowired
    private NoteBookRepository notebookRepository;  // Репозиторий для получения Notebook

    public CellDTO toDTO(Cell cell) {
        return CellDTO.builder()
                .title(cell.getTitle())
                .content(cell.getContent())
                .cellType(cell.getCellType())
                .numberInSequence(cell.getNumberInSequence())
                .notebookId(cell.getNotebook().getId())  // связывание с ноутбуком
                .build();
    }

    public Cell toEntity(CellDTO dto) {
        // Получаем объект Notebook из базы данных по ID
        NoteBook notebook = notebookRepository.findById(dto.getNotebookId())
                .orElseThrow(() -> new IllegalArgumentException("Notebook not found"));

        return Cell.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .cellType(dto.getCellType())
                .numberInSequence(dto.getNumberInSequence())
                .notebook(notebook)  // связывание с объектом ноутбука
                .build();
    }
}
