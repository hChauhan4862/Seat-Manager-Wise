package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.RoomCategoryEntity;
import wise.models.enums.RoomCategoryType;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoomCategoryRepo extends JpaRepository<RoomCategoryEntity, Integer> {

    boolean existsByRoomCatName(RoomCategoryType roomCatName);

    boolean existsByRoomCatCode(Integer roomCatCode);
    
    boolean existsByRoomCatNameAndRoomCatCodeNot(RoomCategoryType roomCatName, Integer roomCatCode);

    @Query(value = "SELECT (COALESCE(MAX(r.ROOMCAT_CODE), 400) + 1) FROM WN_ROOM_CATEGORY r", nativeQuery = true)
    Integer getMaxId();
}
