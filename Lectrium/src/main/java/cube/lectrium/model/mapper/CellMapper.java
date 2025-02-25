package cube.lectrium.model.mapper;

import cube.lectrium.model.Cell;
import cube.lectrium.model.dto.CellDTO;
import org.springframework.stereotype.Component;

@Component
public class CellMapper {

    public CellDTO toDTO(Cell cell) {
        return CellDTO.builder()
                .title(cell.getTitle())
                .content(cell.getContent())
                .cellType(cell.getCellType())
                .numberInSequence(cell.getNumberInSequence())
                .build();
    }

    public Cell toEntity(CellDTO dto) {
        return Cell.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .cellType(dto.getCellType())
                .numberInSequence(dto.getNumberInSequence())
                .build();
    }
}