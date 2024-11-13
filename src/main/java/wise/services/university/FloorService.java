package wise.services.university;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wise.common.CustomException;
import wise.entities.FloorEntity;
import wise.repositories.university.FloorRepo;
import wise.repositories.university.LibraryRepo;

@Service
@RequiredArgsConstructor
public class FloorService {

    private final FloorRepo floorRepo;
    private final LibraryRepo libraryRepo;


    // ------------------------------------- List all floors -------------------------------------
    @Transactional(readOnly = true)
    public Page<FloorEntity> getAllFloors(Pageable pageable) {
        return floorRepo.findAll(pageable);
    }

    // ------------------------------------- Get floor by ID -------------------------------------
    @Transactional(readOnly = true)
    public FloorEntity getFloorById(Integer id) {
        return floorRepo.findById(id)
                .orElseThrow(() -> new CustomException("floor.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new floor -------------------------------------
    @Transactional
    public FloorEntity createFloor(FloorEntity floorEntity) {
        Integer maxId = floorRepo.getMaxId();

        if (floorRepo.existsByFloorName(floorEntity.getFloorName())) {
            throw new CustomException("floor.name.alreadyExists", HttpStatus.CONFLICT);
        }
        if(!libraryRepo.existsByLibCode(floorEntity.getLibCode())){
            throw new CustomException("library.code.notExists", HttpStatus.CONFLICT);
        }
        floorEntity.setFloorCode(maxId);

        return floorRepo.save(floorEntity);
    }

    // ------------------------------------- Update a floor by ID -------------------------------------
    @Transactional
    public FloorEntity updateFloor(FloorEntity floorEntity) {
        if (floorRepo.existsByFloorNameAndFloorCodeNot(floorEntity.getFloorName(), floorEntity.getFloorCode())) {
            throw new CustomException("floor.name.alreadyExistsForAnotherId", HttpStatus.CONFLICT);
        }
        return floorRepo.save(floorEntity);
    }

    // ------------------------------------- Delete a floor by ID -------------------------------------
    @Transactional
    public boolean deleteFloor(Integer id) {
        if (!floorRepo.existsById(id)) {
            throw new CustomException("floor.notFound", HttpStatus.NOT_FOUND);
        }
        floorRepo.deleteById(id);
        return true;
    }
}
