package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.SeatEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SeatRepo extends JpaRepository<SeatEntity, Integer> {

    boolean existsBySeatCode(int seatCode);

    boolean existsBySeatNumber(int seatNumber);

    boolean existsBySeatName(String seatName);

    boolean existsByRoomCode(int roomCode);

    boolean existsByZoneCode(int zoneCode);

    boolean existsBySeatCatCode(String seatCatCode);

    @Query(value = "SELECT (COALESCE(MAX(s.SEAT_CODE), 1000) + 1) FROM WN_SEAT s", nativeQuery = true)
    Integer getMaxId();
}
