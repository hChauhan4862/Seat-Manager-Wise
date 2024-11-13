package wise.controllers.web.booking;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.BookingStatusEntity;
import wise.models.request.booking.BookingStatusRequest;
import wise.models.response.booking.BookingStatusResponse;
import wise.services.booking.BookingStatusService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "bookingStatuses")
@Tag(name = "Booking Status Management", description = "APIs for managing booking statuses")
public class BookingStatusController {

    private static final Logger logger = LoggerFactory.getLogger(BookingStatusController.class);

    private final BookingStatusService bookingStatusService;
    private final ResponseService responseService;

    public BookingStatusController(BookingStatusService bookingStatusService, ResponseService responseService) {
        this.bookingStatusService = bookingStatusService;
        this.responseService = responseService;
    }

    @GetMapping
    @Operation(summary = "Get all Booking Statuses with pagination")
    public ResponseEntity<ApiResponseModel> getAllBookingStatuses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(bookingStatusService.getAllBookingStatuses(pageable)));
    }

    @GetMapping("/{reservationStatusCode}")
    @Operation(summary = "Get Booking Status by reservationStatusCode")
    public ResponseEntity<ApiResponseModel> getBookingStatusByCode(@PathVariable int reservationStatusCode) {
        BookingStatusEntity bookingStatusEntity = bookingStatusService.getBookingStatusByCode(reservationStatusCode);
        BookingStatusResponse response = RequestUtil.NewPatchDataModifier(BookingStatusResponse.class, bookingStatusEntity);
        return ResponseEntity.ok(responseService.successResponse("bookingStatus.found", response));
    }

    @PutMapping("/{reservationStatusCode}")
    @Operation(summary = "Update Booking Status by reservationStatusCode")
    public ResponseEntity<ApiResponseModel> updateBookingStatus(@PathVariable int reservationStatusCode, @Valid @RequestBody BookingStatusRequest request) {
        BookingStatusEntity bookingStatusEntity = RequestUtil.NewPatchDataModifier(BookingStatusEntity.class, request);
        BookingStatusEntity updatedStatus = bookingStatusService.updateBookingStatus(bookingStatusEntity);
        BookingStatusResponse response = RequestUtil.NewPatchDataModifier(BookingStatusResponse.class, updatedStatus);
        logger.info("Booking Status updated successfully: Code={}", reservationStatusCode);
        return ResponseEntity.ok(responseService.successResponse("bookingStatus.updated", response));
    }

    @DeleteMapping("/{reservationStatusCode}")
    @Operation(summary = "Delete Booking Status by reservationStatusCode")
    public ResponseEntity<ApiResponseModel> deleteBookingStatus(@PathVariable int reservationStatusCode) {
        bookingStatusService.deleteBookingStatus(reservationStatusCode);
        logger.info("Booking Status deleted successfully: Code={}", reservationStatusCode);
        return ResponseEntity.ok(responseService.successResponse("bookingStatus.deleted", null));
    }
}
