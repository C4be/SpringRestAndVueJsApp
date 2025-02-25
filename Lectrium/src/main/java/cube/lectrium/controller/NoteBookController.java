package cube.lectrium.controller;

import cube.lectrium.model.dto.NoteBookDTO;
import cube.lectrium.service.NoteBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notebooks")
@RequiredArgsConstructor
public class NoteBookController {

    private final NoteBookService noteBookService;

    @PostMapping("/create")
    public ResponseEntity<NoteBookDTO> createNoteBook(@RequestBody @Valid NoteBookDTO noteBookDTO) {
        return ResponseEntity.ok(noteBookService.createNoteBook(noteBookDTO));
    }

    @GetMapping("/{title}")
    public ResponseEntity<NoteBookDTO> getNoteBook(@PathVariable String title) {
        return ResponseEntity.ok(noteBookService.getNoteBookByTitle(title));
    }

    @PutMapping("/{title}/update")
    public ResponseEntity<NoteBookDTO> updateNoteBook(@PathVariable String title, @RequestBody @Valid NoteBookDTO noteBookDTO) {
        return ResponseEntity.ok(noteBookService.updateNoteBook(title, noteBookDTO));
    }

    @DeleteMapping("/{title}/delete")
    public ResponseEntity<Void> deleteNoteBook(@PathVariable String title) {
        noteBookService.deleteNoteBook(title);
        return ResponseEntity.noContent().build();
    }
}

