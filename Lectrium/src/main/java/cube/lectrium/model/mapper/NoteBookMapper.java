package cube.lectrium.model.mapper;

import cube.lectrium.model.NoteBook;
import cube.lectrium.model.dto.NoteBookDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NoteBookMapper {

    private final CellMapper cellMapper;

    public NoteBookMapper(CellMapper cellMapper) {
        this.cellMapper = cellMapper;
    }

    public NoteBookDTO toDTO(NoteBook notebook) {
        return NoteBookDTO.builder()
                .title(notebook.getTitle())
                .topicId(notebook.getTopic().getId())
                .cells(notebook.getCells().stream().map(cellMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
