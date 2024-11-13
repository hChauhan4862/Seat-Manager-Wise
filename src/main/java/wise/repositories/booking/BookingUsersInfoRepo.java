package wise.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.BookingUsersInfoEntity;

@Repository
public interface BookingUsersInfoRepo extends JpaRepository<BookingUsersInfoEntity, String> {

    boolean existsByBookingId(String bookingId);

    boolean existsByUserId(String userId);

    boolean existsByUserName(String userName);

    boolean existsBySchoolNo(String schoolNo);

    boolean existsByReservationStatusCode(String reservationStatusCode);
}
