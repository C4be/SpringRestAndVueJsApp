package cube.lectrium.service;

import cube.lectrium.model.NoteBook;
import cube.lectrium.model.Topic;
import cube.lectrium.model.dto.NoteBookDTO;
import cube.lectrium.model.mapper.NoteBookMapper;
import cube.lectrium.repository.NoteBookRepository;
import cube.lectrium.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteBookService {

    private final NoteBookRepository noteBookRepository;
    private final TopicRepository topicRepository;
    private final NoteBookMapper noteBookMapper;

    public NoteBookDTO createNoteBook(NoteBookDTO noteBookDTO) {
        Topic topic = topicRepository.findById(noteBookDTO.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        NoteBook noteBook = NoteBook.builder()
                .title(noteBookDTO.getTitle())
                .topic(topic)
                .build();

        return noteBookMapper.toDTO(noteBookRepository.save(noteBook));
    }

    public NoteBookDTO getNoteBookByTitle(String title) {
        return noteBookRepository.findByTitle(title)
                .map(noteBookMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Notebook not found"));
    }

    @Transactional
    public NoteBookDTO updateNoteBook(String title, NoteBookDTO updatedNoteBookDTO) {
        NoteBook noteBook = noteBookRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Notebook not found"));

        if (updatedNoteBookDTO.getTopicId() != null) {
            Topic topic = topicRepository.findById(updatedNoteBookDTO.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
            noteBook.setTopic(topic);
        }

        return noteBookMapper.toDTO(noteBookRepository.save(noteBook));
    }

    @Transactional
    public void deleteNoteBook(String title) {
        NoteBook noteBook = noteBookRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Notebook not found"));
        noteBookRepository.delete(noteBook);
    }
}

