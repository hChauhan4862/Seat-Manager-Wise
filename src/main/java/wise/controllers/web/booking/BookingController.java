package wise.controllers.web.booking;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.BookingEntity;
import wise.models.request.booking.BookingPatchRequest;
import wise.models.request.booking.SeatBookingRequest;
import wise.models.response.booking.BookingResponse;
import wise.services.booking.BookingService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;
    private final ResponseService responseService;

    public BookingController(BookingService bookingService, ResponseService responseService) {
        this.bookingService = bookingService;
        this.responseService = responseService;
    }

    @GetMapping
    @Operation(summary = "Get all Bookings with pagination")
    public ResponseEntity<ApiResponseModel> getAllBookings(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(bookingService.getAllBookings(pageable)));
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get Booking by bookingId")
    public ResponseEntity<ApiResponseModel> getBookingById(@PathVariable String bookingId) {
        BookingEntity bookingEntity = bookingService.getBookingById(bookingId);
        BookingResponse response = RequestUtil.NewPatchDataModifier(BookingResponse.class, bookingEntity);
        return ResponseEntity.ok(responseService.successResponse("booking.found", response));
    }

    @PutMapping("/{bookingId}")
    @Operation(summary = "Update Booking by bookingId")
    public ResponseEntity<ApiResponseModel> updateBooking(@PathVariable String bookingId,
            @Valid @RequestBody SeatBookingRequest request) {
        BookingEntity bookingEntity = RequestUtil.NewPatchDataModifier(BookingEntity.class, request);
        BookingEntity updatedBooking = bookingService.updateBooking(bookingEntity);
        BookingResponse response = RequestUtil.NewPatchDataModifier(BookingResponse.class, updatedBooking);
        logger.info("Booking updated successfully: ID={}", bookingId);
        return ResponseEntity.ok(responseService.successResponse("booking.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a booking by code")
    public ResponseEntity<ApiResponseModel> patchUpdateBooking(@PathVariable String id,
            @Valid @RequestBody BookingPatchRequest bookingPatchRequest) {
        BookingEntity existingBooking = bookingService.getBookingById(id);
        RequestUtil.PatchDataModifier(existingBooking, bookingPatchRequest, true);
        bookingService.updateBooking(existingBooking);
        logger.info("Booking patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("booking.updated", null));
    }
}
