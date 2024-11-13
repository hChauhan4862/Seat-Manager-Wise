package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.RoomSchedulesEntity;
import wise.entities.RoomSchedulesId;

@Repository
public interface RoomSchedulesRepo extends JpaRepository<RoomSchedulesEntity, RoomSchedulesId> {

    // Custom queries can be added here if needed
}
