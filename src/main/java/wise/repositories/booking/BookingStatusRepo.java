package wise.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wise.entities.BookingStatusEntity;
import wise.models.enums.BookingStatus;


@Repository
public interface BookingStatusRepo extends JpaRepository<BookingStatusEntity, Integer> {

    boolean existsByReservationStatusCode(int reservationStatusCode);

    boolean existsByReservationStatusName(BookingStatus reservationStatusName);

     @Query(value = "SELECT (COALESCE(MAX(s.RESERVATION_STATUS_CODE), 1000) + 1) FROM WN_BOOKING_STATUS s", nativeQuery = true)
    Integer getMaxId();
}
