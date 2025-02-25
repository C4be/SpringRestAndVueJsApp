package cube.lectrium.service;

import cube.lectrium.dto.TopicDTO;
import cube.lectrium.mapper.TopicMapper;
import cube.lectrium.model.Topic;
import cube.lectrium.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicMapper topicMapper;

    @InjectMocks
    private TopicService topicService;

    private Topic topic;
    private TopicDTO topicDTO;

    @BeforeEach
    void setUp() {
        topic = Topic.builder().id(1L).title("Test Topic").description("Description").build();
        topicDTO = TopicDTO.builder().title("Test Topic").description("Description").build();
    }

    @Test
    void createTopic_Success() {
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);
        when(topicMapper.toDTO(any(Topic.class))).thenReturn(topicDTO);

        TopicDTO result = topicService.createTopic(topicDTO);

        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle());

        verify(topicRepository, times(1)).save(any(Topic.class));
        verify(topicMapper, times(1)).toDTO(any(Topic.class));
    }

    @Test
    void getAllTopics_Success() {
        when(topicRepository.findAll()).thenReturn(List.of(topic));
        when(topicMapper.toDTO(any(Topic.class))).thenReturn(topicDTO);

        List<TopicDTO> result = topicService.getAllTopics();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Topic", result.get(0).getTitle());

        verify(topicRepository, times(1)).findAll();
        verify(topicMapper, times(1)).toDTO(any(Topic.class));
    }

    @Test
    void getTopicById_Success() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(topicMapper.toDTO(topic)).thenReturn(topicDTO);

        TopicDTO result = topicService.getTopicById(1L);

        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle());

        verify(topicRepository, times(1)).findById(1L);
        verify(topicMapper, times(1)).toDTO(topic);
    }

    @Test
    void getTopicById_NotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> topicService.getTopicById(1L));

        verify(topicRepository, times(1)).findById(1L);
        verify(topicMapper, never()).toDTO(any(Topic.class));
    }

    @Test
    void updateTopic_Success() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);
        when(topicMapper.toDTO(any(Topic.class))).thenReturn(topicDTO);

        TopicDTO updatedDTO = TopicDTO.builder().title("Updated Topic").description("Updated Description").build();
        TopicDTO result = topicService.updateTopic(1L, updatedDTO);

        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle()); // Название остается прежним, т.к. мокируем ответ

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, times(1)).save(any(Topic.class));
        verify(topicMapper, times(1)).toDTO(any(Topic.class));
    }

    @Test
    void updateTopic_NotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        TopicDTO updatedDTO = TopicDTO.builder().title("Updated Topic").description("Updated Description").build();

        assertThrows(EntityNotFoundException.class, () -> topicService.updateTopic(1L, updatedDTO));

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, never()).save(any(Topic.class));
    }

    @Test
    void deleteTopic_Success() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        doNothing().when(topicRepository).delete(topic);

        topicService.deleteTopic(1L);

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, times(1)).delete(topic);
    }

    @Test
    void deleteTopic_NotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> topicService.deleteTopic(1L));

        verify(topicRepository, times(1)).findById(1L);
        verify(topicRepository, never()).delete(any(Topic.class));
    }
}

