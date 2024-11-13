package wise.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.BookingEntity;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, String> {

    boolean existsByBookingId(String bookingId);

    boolean existsByUserId(String userId);

    boolean existsByUserSchoolNo(String userSchoolNo);

    boolean existsByUserName(String userName);

    boolean existsByReservationStatusCode(int reservationStatusCode);

    boolean existsByFloorCode(int floorCode);

    boolean existsByRoomCode(int roomCode);

    boolean existsByZoneCode(int zoneCode);

}
