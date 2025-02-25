package cube.lectrium.controller;

import cube.lectrium.model.dto.CellDTO;
import cube.lectrium.service.CellService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cell")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @PostMapping("/create")
    public ResponseEntity<CellDTO> createCell(@RequestBody @Valid CellDTO cellDTO) {
        return ResponseEntity.ok(cellService.createCell(cellDTO));
    }

    @PutMapping("/{title}/update")
    public ResponseEntity<CellDTO> updateCell(@PathVariable String title, @RequestBody @Valid CellDTO cellDTO) {
        return ResponseEntity.ok(cellService.updateCell(title, cellDTO));
    }

    @GetMapping("/{title}")
    public ResponseEntity<CellDTO> getCell(@PathVariable String title) {
        return ResponseEntity.ok(cellService.getCellByTitle(title));
    }

    @DeleteMapping("/{title}/delete")
    public ResponseEntity<Void> deleteCell(@PathVariable String title) {
        cellService.deleteCell(title);
        return ResponseEntity.noContent().build();
    }
}
