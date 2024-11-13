package wise.services.booking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.BookingStatusEntity;
import wise.repositories.booking.BookingStatusRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookingStatusService {

    private final BookingStatusRepo bookingStatusRepo;

    public BookingStatusService(BookingStatusRepo bookingStatusRepo) {
        this.bookingStatusRepo = bookingStatusRepo;
    }

    // ------------------------------------- List all Booking Statuses -------------------------------------
    @Transactional(readOnly = true)
    public Page<BookingStatusEntity> getAllBookingStatuses(Pageable pageable) {
        return bookingStatusRepo.findAll(pageable);
    }

    // ------------------------------------- Get Booking Status by reservationStatusCode -------------------------------------
    @Transactional(readOnly = true)
    public BookingStatusEntity getBookingStatusByCode(int reservationStatusCode) {
        return bookingStatusRepo.findById(reservationStatusCode)
                .orElseThrow(() -> new CustomException("bookingStatus.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Update Booking Status -------------------------------------
    @Transactional
    public BookingStatusEntity updateBookingStatus(BookingStatusEntity bookingStatusEntity) {

        return bookingStatusRepo.save(bookingStatusEntity);
    }

    // ------------------------------------- Delete Booking Status by reservationStatusCode -------------------------------------
    @Transactional
    public boolean deleteBookingStatus(int reservationStatusCode) {
        if (!bookingStatusRepo.existsByReservationStatusCode(reservationStatusCode)) {
            throw new CustomException("bookingStatus.notFound", HttpStatus.NOT_FOUND);
        }
        bookingStatusRepo.deleteById(reservationStatusCode);
        return true;
    }
}
