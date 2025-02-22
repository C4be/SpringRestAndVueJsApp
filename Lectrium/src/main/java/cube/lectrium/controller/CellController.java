package cube.lectrium.controller;

import cube.lectrium.model.Cell;
import cube.lectrium.repository.dto.CellDataRequest;
import cube.lectrium.service.CellService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cell")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @PostMapping("/{title}/create")
    public Cell createCell(@PathVariable String title, @RequestBody @Valid CellDataRequest request) {
        return cellService.createCellByTitle(title, request.getContent(), request.getCellType());
    }

    @PutMapping("/{title}/update")
    public Cell updateCell(@PathVariable String title, @RequestBody @Valid CellDataRequest request) {
        return cellService.updateCellByTitle(title, request.getContent(), request.getCellType());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Cell> getCell(@PathVariable String title) {
        Cell cell = cellService.getCellByTitle(title);
        return cell != null ? ResponseEntity.ok(cell) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Cell> getAllCells() {
        return cellService.getAllCells();
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Void> deleteCell(@PathVariable String title) {
        boolean deleted = cellService.deleteCellByTitle(title);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
