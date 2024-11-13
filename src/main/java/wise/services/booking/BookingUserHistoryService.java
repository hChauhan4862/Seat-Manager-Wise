package wise.services.booking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.entities.BookingUserHistoryEntity;
import wise.repositories.booking.BookingUserHistoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wise.common.CustomException;

@Service
public class BookingUserHistoryService {

    private final BookingUserHistoryRepo bookingUserHistoryRepo;

    public BookingUserHistoryService(BookingUserHistoryRepo bookingUserHistoryRepo) {
        this.bookingUserHistoryRepo = bookingUserHistoryRepo;
    }

    // ------------------------------------- List all Booking User History -------------------------------------
    @Transactional(readOnly = true)
    public Page<BookingUserHistoryEntity> getAllBookingUserHistory(Pageable pageable) {
        return bookingUserHistoryRepo.findAll(pageable);
    }

    // ------------------------------------- Get Booking User History by bookingId -------------------------------------
    @Transactional(readOnly = true)
    public BookingUserHistoryEntity getBookingUserHistoryById(String bookingId) {
        return bookingUserHistoryRepo.findById(bookingId)
                .orElseThrow(() -> new CustomException("bookingUserHistory.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Delete Booking User History by bookingId -------------------------------------
    @Transactional
    public boolean deleteBookingUserHistory(String bookingId) {
        if (!bookingUserHistoryRepo.existsByBookingId(bookingId)) {
            throw new CustomException("bookingUserHistory.notFound", HttpStatus.NOT_FOUND);
        }
        bookingUserHistoryRepo.deleteById(bookingId);
        return true;
    }
}
