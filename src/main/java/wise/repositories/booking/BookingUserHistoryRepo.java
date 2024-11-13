package wise.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.BookingUserHistoryEntity;
import wise.models.enums.BookingActionCode;


@Repository
public interface BookingUserHistoryRepo extends JpaRepository<BookingUserHistoryEntity, String> {

    boolean existsByBookingId(String bookingId);

    boolean existsByUserId(String userId);

    boolean existsByUserName(String userName);

    boolean existsBySchoolNo(String schoolNo);

    boolean existsByReservationStatusCode(String reservationStatusCode);

    boolean existsByActionCode(BookingActionCode actionCode);
}
