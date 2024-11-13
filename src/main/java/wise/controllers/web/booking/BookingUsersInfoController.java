package wise.controllers.web.booking;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.BookingUsersInfoEntity;
import wise.models.request.booking.BookingUsersInfoPatchRequest;
import wise.models.request.booking.BookingUsersInfoRequest;
import wise.models.response.booking.BookingUsersInfoResponse;
import wise.services.booking.BookingUsersInfoService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "booking-users-info")
@Tag(name = "Booking Users Info Management", description = "APIs for managing booking user information")
public class BookingUsersInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BookingUsersInfoController.class);

    private final BookingUsersInfoService bookingUsersInfoService;
    private final ResponseService responseService;

    public BookingUsersInfoController(BookingUsersInfoService bookingUsersInfoService,
            ResponseService responseService) {
        this.bookingUsersInfoService = bookingUsersInfoService;
        this.responseService = responseService;
    }

    @GetMapping
    @Operation(summary = "Get all Booking Users Info with pagination")
    public ResponseEntity<ApiResponseModel> getAllBookingUsersInfo(@PageableDefault Pageable pageable) {
        return ResponseEntity
                .ok(responseService.successResponse(bookingUsersInfoService.getAllBookingUsersInfo(pageable)));
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get Booking User Info by bookingId")
    public ResponseEntity<ApiResponseModel> getBookingUsersInfoById(@PathVariable String bookingId) {
        BookingUsersInfoEntity bookingUsersInfoEntity = bookingUsersInfoService.getBookingUsersInfoById(bookingId);
        BookingUsersInfoResponse response = RequestUtil.NewPatchDataModifier(BookingUsersInfoResponse.class,
                bookingUsersInfoEntity);
        return ResponseEntity.ok(responseService.successResponse("bookingUsersInfo.found", response));
    }

    @PutMapping("/{bookingId}")
    @Operation(summary = "Update Booking User Info by bookingId")
    public ResponseEntity<ApiResponseModel> updateBookingUsersInfo(@PathVariable String bookingId,
            @Valid @RequestBody BookingUsersInfoRequest request) {
        BookingUsersInfoEntity bookingUsersInfoEntity = RequestUtil.NewPatchDataModifier(BookingUsersInfoEntity.class,
                request);
        BookingUsersInfoEntity updatedInfo = bookingUsersInfoService.updateBookingUsersInfo(bookingUsersInfoEntity);
        BookingUsersInfoResponse response = RequestUtil.NewPatchDataModifier(BookingUsersInfoResponse.class,
                updatedInfo);
        logger.info("Booking User Info updated successfully: ID={}", bookingId);
        return ResponseEntity.ok(responseService.successResponse("bookingUsersInfo.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a booking user info by code")
    public ResponseEntity<ApiResponseModel> patchUpdateBookingUserInfo(@PathVariable String id,
            @Valid @RequestBody BookingUsersInfoPatchRequest bookingUserInfoPatchRequest) {
        BookingUsersInfoEntity existingBookingUserInfo = bookingUsersInfoService.getBookingUsersInfoById(id);
        RequestUtil.PatchDataModifier(existingBookingUserInfo, bookingUserInfoPatchRequest, true);
        bookingUsersInfoService.updateBookingUsersInfo(existingBookingUserInfo);
        logger.info("BookingUserInfo patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("bookingUserInfo.updated", null));
    }

    @DeleteMapping("/{bookingId}")
    @Operation(summary = "Delete Booking User Info by bookingId")
    public ResponseEntity<ApiResponseModel> deleteBookingUsersInfo(@PathVariable String bookingId) {
        bookingUsersInfoService.deleteBookingUsersInfo(bookingId);
        logger.info("Booking User Info deleted successfully: ID={}", bookingId);
        return ResponseEntity.ok(responseService.successResponse("bookingUsersInfo.deleted", null));
    }
}
