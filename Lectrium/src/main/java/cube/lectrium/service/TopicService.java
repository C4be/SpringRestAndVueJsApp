package cube.lectrium.service;

import cube.lectrium.model.Topic;
import cube.lectrium.model.dto.TopicDTO;
import cube.lectrium.model.mapper.TopicMapper;
import cube.lectrium.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicDTO createTopic(TopicDTO topicDTO) {
        Topic topic = Topic.builder()
                .title(topicDTO.getTitle())
                .description(topicDTO.getDescription())
                .build();

        return topicMapper.toDTO(topicRepository.save(topic));
    }

    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TopicDTO getTopicById(Long id) {
        return topicRepository.findById(id)
                .map(topicMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
    }

    @Transactional
    public TopicDTO updateTopic(Long id, TopicDTO topicDTO) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        topic.setTitle(topicDTO.getTitle());
        topic.setDescription(topicDTO.getDescription());

        return topicMapper.toDTO(topicRepository.save(topic));
    }

    @Transactional
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }
}
