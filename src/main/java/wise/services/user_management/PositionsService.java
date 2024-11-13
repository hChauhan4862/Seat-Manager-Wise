package wise.services.user_management;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.common.CustomException;
import wise.entities.PositionEntity;
import wise.repositories.user_management.PositionRepo;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Service
public class PositionsService {

    private PositionRepo posRepo;

    public PositionsService(PositionRepo posRepo) {
        this.posRepo = posRepo;
    }

    // ------------------------------------- List all positions -------------------------------------
    @Transactional(readOnly = true)
    public List<PositionEntity> getAll() {
        return posRepo.findAll();
    }

    // ------------------------------------- List all positions with pagination -------------------------------------
    @Transactional(readOnly = true)
    public Page<PositionEntity> getAllPositions(Pageable pagable) {
        return posRepo.findAll(pagable);
    }

    
    // ------------------------------------- Get position by ID -------------------------------------
    @Transactional(readOnly = true)
    public PositionEntity getByCode(String code) {
        return posRepo.findById(code)
            .map(position -> {
                return position;
            })
            .orElseThrow(() -> new CustomException("positions.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new position -------------------------------------
    @Transactional
    public PositionEntity create(PositionEntity posEntity) {
        // Find Max Code 1,8 characters and increment by 1
        if (posEntity.getPosCode() != null && posRepo.existsByPosCode(posEntity.getPosCode())) {
            throw new CustomException("positions.code.alreadyExists", HttpStatus.CONFLICT);
        } else if (posEntity.getPosCode() == null) {
            posEntity.setPosCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
        }

        // Check if position already exists in the database
        if(posRepo.existsByPosName(posEntity.getPosName())) {
            throw new CustomException("positions.name.alreadyExists", HttpStatus.CONFLICT);
        }
        return posRepo.save(posEntity);
    }

    // ------------------------------------- Update a position by ID -------------------------------------
    public PositionEntity updatePosition(PositionEntity posEntity) {

        if(posRepo.existsByPosNameAndPosCodeNot(posEntity.getPosName(), posEntity.getPosCode())) {
            throw new CustomException("positions.name.alreadyExistsForAnotherCode", HttpStatus.CONFLICT);
        }

        return posRepo.save(posEntity);
    }

    // ------------------------------------- Delete a position by ID -------------------------------------
    @Transactional
    public boolean deletePosition(String code) {
        if(!posRepo.existsById(code)) {
            throw new CustomException("positions.notFound", HttpStatus.NOT_FOUND);
        }

        posRepo.deleteById(code);
        return true;
    }
}
