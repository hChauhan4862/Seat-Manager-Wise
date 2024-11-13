package wise.repositories.university;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wise.entities.RoomEntity;

@Repository
public interface RoomRepo extends JpaRepository<RoomEntity, Integer> {
    
    // Exists by room code
    boolean existsByRoomCode(Integer roomCode);

    // Exists by room name and floor code
    boolean existsByRoomNameAndFloorCode(String roomName, Integer floorCode);

    // Find by room code
    Optional<RoomEntity> findByRoomCode(Integer roomCode);

    // Find by room name
    Optional<RoomEntity> findByRoomName(String roomName);

    // Exists by room number and floor code
    boolean existsByRoomNumberAndFloorCode(String roomNumber, Integer floorCode);

    // Fetch the next available room code
    @Query( value = "SELECT (COALESCE(MAX(r.ROOM_CODE), 500) + 1) FROM WN_ROOM r", nativeQuery = true)
    Integer getNextRoomCode();
}
