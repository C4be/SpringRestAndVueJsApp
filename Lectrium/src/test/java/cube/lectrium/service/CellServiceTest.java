package cube.lectrium.service;

import cube.lectrium.dto.CellDTO;
import cube.lectrium.mapper.CellMapper;
import cube.lectrium.model.Cell;
import cube.lectrium.model.NoteBook;
import cube.lectrium.repository.CellRepository;
import cube.lectrium.repository.NoteBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CellServiceTest {

    @Mock
    private CellRepository cellRepository;

    @Mock
    private CellMapper cellMapper;

    @Mock
    private NoteBookRepository noteBookRepository;

    @InjectMocks
    private CellService cellService;

    @Test
    void getCellByTitle() {
        Cell cell = Cell.builder()
                .title("Test1")
                .cellType(Cell.CellType.PYTHON_CELL)
                .content("Some text as content")
                .notebook(new NoteBook())
                .numberInSequence(1)
                .build();

        CellDTO cellDTO = CellDTO.builder()
                .title("Test1")
                .cellType(Cell.CellType.PYTHON_CELL)
                .content("Some text as content")
                .notebookId(1L)
                .numberInSequence(1)
                .build();

        when(cellRepository.findByTitle("Test1")).thenReturn(Optional.of(cell));
        when(cellMapper.toDTO(any())).thenReturn(cellDTO);

        CellDTO result = cellService.getCellByTitle("Test1");

        assertNotNull(result);
        assertEquals(cellDTO.getTitle(), result.getTitle());
        assertEquals(cellDTO.getContent(), result.getContent());
        assertEquals(cellDTO.getCellType(), result.getCellType());
        assertEquals(cellDTO.getNumberInSequence(), result.getNumberInSequence());

        verify(cellRepository, times(1)).findByTitle("Test1");
        verify(cellMapper, times(1)).toDTO(any(Cell.class));
    }

    @Test
    void createCell() {
        CellDTO cellDTO = CellDTO.builder()
                .title("Test2")
                .content("Some content")
                .cellType(Cell.CellType.JAVA_CELL)
                .notebookId(1L)
                .numberInSequence(2)
                .build();

        NoteBook notebook = new NoteBook();
        notebook.setId(1L);

        Cell cell = Cell.builder()
                .title("Test2")
                .content("Some content")
                .cellType(Cell.CellType.JAVA_CELL)
                .notebook(notebook)
                .numberInSequence(2)
                .build();

        lenient().when(noteBookRepository.findById(1L)).thenReturn(Optional.of(notebook));
        when(cellMapper.toEntity(cellDTO)).thenReturn(cell);
        when(cellRepository.save(cell)).thenReturn(cell);
        when(cellMapper.toDTO(cell)).thenReturn(cellDTO);

        CellDTO result = cellService.createCell(cellDTO);

        assertNotNull(result);
        assertEquals(cellDTO.getTitle(), result.getTitle());
        assertEquals(cellDTO.getContent(), result.getContent());

        verify(cellRepository, times(1)).save(any(Cell.class));
    }

    @Test
    void updateCell() {
        Cell existingCell = Cell.builder()
                .title("Test3")
                .content("Old content")
                .cellType(Cell.CellType.MARKDOWN_CELL)
                .numberInSequence(3)
                .notebook(new NoteBook())
                .build();

        CellDTO updatedCellDTO = CellDTO.builder()
                .title("Test3")
                .content("Updated content")
                .cellType(Cell.CellType.MARKDOWN_CELL)
                .numberInSequence(3)
                .notebookId(1L)
                .build();

        when(cellRepository.findByTitle("Test3")).thenReturn(Optional.of(existingCell));
        when(cellRepository.save(existingCell)).thenReturn(existingCell);
        when(cellMapper.toDTO(existingCell)).thenReturn(updatedCellDTO);

        CellDTO result = cellService.updateCell("Test3", updatedCellDTO);

        assertNotNull(result);
        assertEquals("Updated content", result.getContent());

        verify(cellRepository, times(1)).findByTitle("Test3");
        verify(cellRepository, times(1)).save(existingCell);
    }

    @Test
    void deleteCell() {
        Cell cell = Cell.builder()
                .title("Test4")
                .content("Some content")
                .cellType(Cell.CellType.ROW_TEXT_CELL)
                .numberInSequence(4)
                .notebook(new NoteBook())
                .build();

        when(cellRepository.findByTitle("Test4")).thenReturn(Optional.of(cell));

        boolean result = cellService.deleteCell("Test4");

        assertTrue(result);

        verify(cellRepository, times(1)).delete(cell);
    }
}

