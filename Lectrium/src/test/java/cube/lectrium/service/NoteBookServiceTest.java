package cube.lectrium.service;


import cube.lectrium.dto.NoteBookDTO;
import cube.lectrium.mapper.NoteBookMapper;
import cube.lectrium.model.NoteBook;
import cube.lectrium.model.Topic;
import cube.lectrium.repository.NoteBookRepository;
import cube.lectrium.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteBookServiceTest {

    @Mock
    private NoteBookRepository noteBookRepository;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private NoteBookMapper noteBookMapper;

    @InjectMocks
    private NoteBookService noteBookService;

    private Topic topic;
    private NoteBook noteBook;
    private NoteBookDTO noteBookDTO;

    @BeforeEach
    void setUp() {
        topic = Topic.builder().id(1L).title("Test Topic").description("Description").build();
        noteBook = NoteBook.builder().id(1L).title("Test Notebook").topic(topic).build();
        noteBookDTO = NoteBookDTO.builder().title("Test Notebook").topicId(1L).build();
    }

    @Test
    void createNoteBook_Success() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(noteBookRepository.save(any(NoteBook.class))).thenReturn(noteBook);
        when(noteBookMapper.toDTO(any(NoteBook.class))).thenReturn(noteBookDTO);

        NoteBookDTO result = noteBookService.createNoteBook(noteBookDTO);

        assertNotNull(result);
        assertEquals("Test Notebook", result.getTitle());

        verify(topicRepository, times(1)).findById(1L);
        verify(noteBookRepository, times(1)).save(any(NoteBook.class));
        verify(noteBookMapper, times(1)).toDTO(any(NoteBook.class));
    }

    @Test
    void createNoteBook_TopicNotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> noteBookService.createNoteBook(noteBookDTO));

        verify(topicRepository, times(1)).findById(1L);
        verify(noteBookRepository, never()).save(any(NoteBook.class));
    }

    @Test
    void getNoteBookByTitle_Success() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.of(noteBook));
        when(noteBookMapper.toDTO(noteBook)).thenReturn(noteBookDTO);

        NoteBookDTO result = noteBookService.getNoteBookByTitle("Test Notebook");

        assertNotNull(result);
        assertEquals("Test Notebook", result.getTitle());

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
        verify(noteBookMapper, times(1)).toDTO(noteBook);
    }

    @Test
    void getNoteBookByTitle_NotFound() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> noteBookService.getNoteBookByTitle("Test Notebook"));

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
    }

    @Test
    void updateNoteBook_Success() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.of(noteBook));
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(noteBookRepository.save(any(NoteBook.class))).thenReturn(noteBook);
        when(noteBookMapper.toDTO(any(NoteBook.class))).thenReturn(noteBookDTO);

        NoteBookDTO updatedDTO = NoteBookDTO.builder().title("Updated Notebook").topicId(1L).build();
        NoteBookDTO result = noteBookService.updateNoteBook("Test Notebook", updatedDTO);

        assertNotNull(result);
        assertEquals("Test Notebook", result.getTitle());

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
        verify(noteBookRepository, times(1)).save(any(NoteBook.class));
        verify(noteBookMapper, times(1)).toDTO(any(NoteBook.class));
    }

    @Test
    void updateNoteBook_NotFound() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.empty());

        NoteBookDTO updatedDTO = NoteBookDTO.builder().title("Updated Notebook").topicId(1L).build();

        assertThrows(EntityNotFoundException.class, () -> noteBookService.updateNoteBook("Test Notebook", updatedDTO));

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
        verify(noteBookRepository, never()).save(any(NoteBook.class));
    }

    @Test
    void deleteNoteBook_Success() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.of(noteBook));
        doNothing().when(noteBookRepository).delete(noteBook);

        noteBookService.deleteNoteBook("Test Notebook");

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
        verify(noteBookRepository, times(1)).delete(noteBook);
    }

    @Test
    void deleteNoteBook_NotFound() {
        when(noteBookRepository.findByTitle("Test Notebook")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> noteBookService.deleteNoteBook("Test Notebook"));

        verify(noteBookRepository, times(1)).findByTitle("Test Notebook");
        verify(noteBookRepository, never()).delete(any(NoteBook.class));
    }
}

