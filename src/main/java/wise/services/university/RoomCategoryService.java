package wise.services.university;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.RoomCategoryEntity;
import wise.repositories.university.RoomCategoryRepo;

@Service
public class RoomCategoryService {

    private final RoomCategoryRepo roomCategoryRepo;

    public RoomCategoryService(RoomCategoryRepo roomCategoryRepo) {
        this.roomCategoryRepo = roomCategoryRepo;
    }

    // ------------------------------------- List all room categories -------------------------------------
    @Transactional(readOnly = true)
    public Page<RoomCategoryEntity> getAllRoomCategories(Pageable pageable) {
        return roomCategoryRepo.findAll(pageable);
    }

    // ------------------------------------- Get room category by ID -------------------------------------
    @Transactional(readOnly = true)
    public RoomCategoryEntity getRoomCategoryById(Integer id) {
        return roomCategoryRepo.findById(id)
                .orElseThrow(() -> new CustomException("roomCategory.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new room category -------------------------------------
    @Transactional
    public RoomCategoryEntity createRoomCategory(RoomCategoryEntity roomCategoryEntity) {
        Integer maxId = roomCategoryRepo.getMaxId();

        if (roomCategoryRepo.existsByRoomCatName(roomCategoryEntity.getRoomCatName())) {
            throw new CustomException("roomCategory.name.alreadyExists", HttpStatus.CONFLICT);
        }

        roomCategoryEntity.setRoomCatCode(maxId);

        return roomCategoryRepo.save(roomCategoryEntity);
    }

    // ------------------------------------- Update a room category by ID -------------------------------------
    @Transactional
    public RoomCategoryEntity updateRoomCategory(RoomCategoryEntity roomCategoryEntity) {
        if (roomCategoryRepo.existsByRoomCatNameAndRoomCatCodeNot(roomCategoryEntity.getRoomCatName(), roomCategoryEntity.getRoomCatCode())) {
            throw new CustomException("roomCategory.name.alreadyExistsForAnotherId", HttpStatus.CONFLICT);
        }
        return roomCategoryRepo.save(roomCategoryEntity);
    }

    // ------------------------------------- Delete a room category by ID -------------------------------------
    @Transactional
    public boolean deleteRoomCategory(Integer id) {
        if (!roomCategoryRepo.existsById(id)) {
            throw new CustomException("roomCategory.notFound", HttpStatus.NOT_FOUND);
        }
        roomCategoryRepo.deleteById(id);
        return true;
    }
}
