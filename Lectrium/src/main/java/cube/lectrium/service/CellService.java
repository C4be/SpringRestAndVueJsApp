package cube.lectrium.service;

import cube.lectrium.model.Cell;
import cube.lectrium.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;

    public Cell getCellByTitle(String title) {
        return cellRepository.getCellByTitle(title);
    }

    @Transactional
    public Cell createCellByTitle(String title, String content, Cell.CellType type) {
        Cell newCell = Cell.builder()
                .title(title)
                .content(content)
                .cellType(type)
                .build();

        return cellRepository.save(newCell);
    }

    @Transactional
    public Cell updateCellByTitle(String title, String content, Cell.CellType type) {
        Cell cell = getCellByTitle(title);
        if (cell != null) {
            cell.setContent(content);
            cell.setCellType(type);
            return cellRepository.save(cell);
        }
        return null;
    }

    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }

    @Transactional
    public boolean deleteCellByTitle(String title) {
        Cell cell = getCellByTitle(title);
        if (cell != null) {
            cellRepository.delete(cell);
            return true;
        }
        return false;
    }
}