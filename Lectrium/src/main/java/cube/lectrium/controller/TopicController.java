package cube.lectrium.controller;

import cube.lectrium.model.dto.TopicDTO;
import cube.lectrium.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/create")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid TopicDTO topicDTO) {
        return ResponseEntity.ok(topicService.createTopic(topicDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicDTO topicDTO) {
        return ResponseEntity.ok(topicService.updateTopic(id, topicDTO));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
