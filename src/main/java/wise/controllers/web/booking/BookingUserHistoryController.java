package wise.controllers.web.booking;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.BookingUserHistoryEntity;
import wise.models.response.booking.BookingUserHistoryResponse;
import wise.services.booking.BookingUserHistoryService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "booking-user-history")
@Tag(name = "Booking User History Management", description = "APIs for managing booking user history")
public class BookingUserHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(BookingUserHistoryController.class);

    private final BookingUserHistoryService bookingUserHistoryService;
    private final ResponseService responseService;

    public BookingUserHistoryController(BookingUserHistoryService bookingUserHistoryService,
            ResponseService responseService) {
        this.bookingUserHistoryService = bookingUserHistoryService;
        this.responseService = responseService;
    }

    @GetMapping
    @Operation(summary = "Get all Booking User Histories with pagination")
    public ResponseEntity<ApiResponseModel> getAllBookingUserHistory(@PageableDefault Pageable pageable) {
        return ResponseEntity
                .ok(responseService.successResponse(bookingUserHistoryService.getAllBookingUserHistory(pageable)));
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get Booking User History by bookingId")
    public ResponseEntity<ApiResponseModel> getBookingUserHistoryById(@PathVariable String bookingId) {
        BookingUserHistoryEntity bookingUserHistoryEntity = bookingUserHistoryService
                .getBookingUserHistoryById(bookingId);
        BookingUserHistoryResponse response = RequestUtil.NewPatchDataModifier(BookingUserHistoryResponse.class,
                bookingUserHistoryEntity);
        return ResponseEntity.ok(responseService.successResponse("bookingUserHistory.found", response));
    }

    @DeleteMapping("/{bookingId}")
    @Operation(summary = "Delete Booking User History by bookingId")
    public ResponseEntity<ApiResponseModel> deleteBookingUserHistory(@PathVariable String bookingId) {
        bookingUserHistoryService.deleteBookingUserHistory(bookingId);
        logger.info("Booking User History deleted successfully: ID={}", bookingId);
        return ResponseEntity.ok(responseService.successResponse("bookingUserHistory.deleted", null));
    }
}
