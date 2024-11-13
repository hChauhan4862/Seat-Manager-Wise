package wise.services.booking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.entities.BookingUsersInfoEntity;
import wise.repositories.booking.BookingUsersInfoRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wise.common.CustomException;

@Service
public class BookingUsersInfoService {

    private final BookingUsersInfoRepo bookingUsersInfoRepo;

    public BookingUsersInfoService(BookingUsersInfoRepo bookingUsersInfoRepo) {
        this.bookingUsersInfoRepo = bookingUsersInfoRepo;
    }

    // ------------------------------------- List all Booking Users Info
    // -------------------------------------
    @Transactional(readOnly = true)
    public Page<BookingUsersInfoEntity> getAllBookingUsersInfo(Pageable pageable) {
        return bookingUsersInfoRepo.findAll(pageable);
    }

    // ------------------------------------- Get Booking User Info by bookingId
    // -------------------------------------
    @Transactional(readOnly = true)
    public BookingUsersInfoEntity getBookingUsersInfoById(String bookingId) {
        return bookingUsersInfoRepo.findById(bookingId)
                .orElseThrow(() -> new CustomException("bookingUsersInfo.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Update Booking User Info
    // -------------------------------------
    @Transactional
    public BookingUsersInfoEntity updateBookingUsersInfo(BookingUsersInfoEntity bookingUsersInfoEntity) {

        return bookingUsersInfoRepo.save(bookingUsersInfoEntity);
    }


    /**
     * @apiNote Delete Booking User Info by bookingId
     * @param bookingId
     * @return
     * @author Gyanendra Kumar
     */
    @Transactional
    public boolean deleteBookingUsersInfo(String bookingId) {
        if (!bookingUsersInfoRepo.existsByBookingId(bookingId)) {
            throw new CustomException("bookingUsersInfo.notFound", HttpStatus.NOT_FOUND);
        }
        bookingUsersInfoRepo.deleteById(bookingId);
        return true;
    }
}
