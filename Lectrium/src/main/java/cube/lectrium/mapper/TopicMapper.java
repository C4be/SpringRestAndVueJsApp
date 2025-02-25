package cube.lectrium.mapper;

import cube.lectrium.model.Topic;
import cube.lectrium.dto.TopicDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TopicMapper {

    private final NoteBookMapper noteBookMapper;

    public TopicMapper(NoteBookMapper noteBookMapper) {
        this.noteBookMapper = noteBookMapper;
    }

    public TopicDTO toDTO(Topic topic) {
        return TopicDTO.builder()
                .title(topic.getTitle())
                .description(topic.getDescription())
                .notebooks(topic.getNotebooks().stream().map(noteBookMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
