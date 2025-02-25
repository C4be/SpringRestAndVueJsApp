package cube.lectrium.service;

import cube.lectrium.model.Cell;
import cube.lectrium.model.dto.CellDTO;
import cube.lectrium.model.mapper.CellMapper;
import cube.lectrium.repository.CellRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;
    private final CellMapper cellMapper;

    public CellDTO getCellByTitle(String title) {
        return cellRepository.findByTitle(title)
                .map(cellMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found"));
    }

    @Transactional
    public CellDTO createCell(CellDTO cellDTO) {
        Cell cell = cellMapper.toEntity(cellDTO);
        return cellMapper.toDTO(cellRepository.save(cell));
    }

    @Transactional
    public CellDTO updateCell(String title, CellDTO cellDTO) {
        Cell cell = cellRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found"));

        cell.setContent(cellDTO.getContent());
        cell.setCellType(cellDTO.getCellType());
        return cellMapper.toDTO(cellRepository.save(cell));
    }

    @Transactional
    public boolean deleteCell(String title) {
        Cell cell = cellRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found"));
        cellRepository.delete(cell);
        return true;
    }
}
