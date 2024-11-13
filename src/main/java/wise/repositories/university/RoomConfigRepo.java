package wise.repositories.university;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.RoomConfigEntity;
import wise.entities.RoomConfigId;

@Repository
public interface RoomConfigRepo extends JpaRepository<RoomConfigEntity, RoomConfigId> {
    boolean existsByRoomConfigId_RoomCode(int roomCode);

    Optional<RoomConfigEntity> findByRoomConfigId_RoomCode(int roomCode);

    boolean existsByCommonOpenTime(String commonOpenTime);

    boolean existsByCommonCloseTime(String commonCloseTime);

    void deleteByRoomConfigId_RoomCode(int roomCode);
}
