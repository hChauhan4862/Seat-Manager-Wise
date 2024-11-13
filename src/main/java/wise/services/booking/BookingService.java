package wise.services.booking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.BookingEntity;
import wise.models.enums.BookingType;
import wise.models.request.booking.SeatBookingRequest;
import wise.repositories.booking.BookingRepo;
import wise.utils.DateTime;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;

    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    // ------------------------------------- List all Bookings
    // -------------------------------------
    @Transactional(readOnly = true)
    public Page<BookingEntity> getAllBookings(Pageable pageable) {
        return bookingRepo.findAll(pageable);
    }

    // ------------------------------------- Get Booking by bookingId
    // -------------------------------------
    @Transactional(readOnly = true)
    public BookingEntity getBookingById(String bookingId) {
        return bookingRepo.findById(bookingId)
                .orElseThrow(() -> new CustomException("booking.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new Booking
    // -------------------------------------
    @Transactional
    public BookingEntity createBooking(SeatBookingRequest seatBookingRequest) {


        // return bookingRepo.save(bookingEntity);
        return new BookingEntity();
    }

    // ------------------------------------- Update Booking
    // -------------------------------------
    @Transactional
    public BookingEntity updateBooking(BookingEntity bookingEntity) {

        return bookingRepo.save(bookingEntity);
    }

    /**
     * @apiNote Booking Id Generator
     * @param bookingEntity
     * @return
     */
    public String generateBookingId(BookingEntity bookingEntity) {
        int reservationCode = bookingEntity.getReserveDeskCode();
        BookingType typecode = bookingEntity.getBookingTypeCode();
        String currentDateTime = new DateTime().getDateTimeString();
        String bookingId = currentDateTime + typecode.toString().charAt(0) + reservationCode;
        return bookingId;
    }
}
