package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.SeatEntity;
import wise.models.request.university.SeatPatchRequest;
import wise.models.request.university.SeatRequest;
import wise.models.response.university.SeatResponse;
import wise.services.university.SeatService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "seats")
@Tag(name = "Seat Management", description = "APIs for managing seats")
public class SeatController {

    private static final Logger logger = LoggerFactory.getLogger(SeatController.class);

    private final SeatService seatService;
    private final ResponseService responseService;

    public SeatController(SeatService seatService, ResponseService responseService) {
        this.seatService = seatService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new seat")
    public ResponseEntity<ApiResponseModel> createSeat(@Valid @RequestBody SeatRequest seatRequest) {
        SeatEntity seatEntity = RequestUtil.NewPatchDataModifier(SeatEntity.class, seatRequest);
        SeatEntity createdSeat = seatService.createSeat(seatEntity);
        SeatResponse response = RequestUtil.NewPatchDataModifier(SeatResponse.class, createdSeat);
        logger.info("Seat created successfully: Code={}", createdSeat.getSeatCode());
        return ResponseEntity.ok(responseService.successResponse("seat.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all seats with pagination")
    public ResponseEntity<ApiResponseModel> getAllSeats(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(seatService.getAllSeats(pageable)));
    }

    @GetMapping("/{seatCode}")
    @Operation(summary = "Get a seat by code")
    public ResponseEntity<ApiResponseModel> getSeatByCode(@PathVariable int seatCode) {
        SeatEntity seatEntity = seatService.getSeatByCode(seatCode);
        SeatResponse response = RequestUtil.NewPatchDataModifier(SeatResponse.class, seatEntity);
        return ResponseEntity.ok(responseService.successResponse("seat.found", response));
    }

    @PutMapping("/{seatCode}")
    @Operation(summary = "Update a seat by code")
    public ResponseEntity<ApiResponseModel> updateSeat(@PathVariable int seatCode,
            @Valid @RequestBody SeatRequest seatRequest) {
        SeatEntity seatEntity = RequestUtil.NewPatchDataModifier(SeatEntity.class, seatRequest);
        SeatEntity updatedSeat = seatService.updateSeat(seatEntity);
        SeatResponse response = RequestUtil.NewPatchDataModifier(SeatResponse.class, updatedSeat);
        logger.info("Seat updated successfully: Code={}", seatCode);
        return ResponseEntity.ok(responseService.successResponse("seat.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a seat by code")
    public ResponseEntity<ApiResponseModel> patchUpdateSeat(@PathVariable int id,
            @Valid @RequestBody SeatPatchRequest seatPatchRequest) {
        SeatEntity existingSeat = seatService.getSeatByCode(id);
        RequestUtil.PatchDataModifier(existingSeat, seatPatchRequest, true);
        seatService.updateSeat(existingSeat);
        logger.info("Seat patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("seat.updated", null));
    }

    @DeleteMapping("/{seatCode}")
    @Operation(summary = "Delete a seat by code")
    public ResponseEntity<ApiResponseModel> deleteSeat(@PathVariable int seatCode) {
        seatService.deleteSeat(seatCode);
        logger.info("Seat deleted successfully: Code={}", seatCode);
        return ResponseEntity.ok(responseService.successResponse("seat.deleted", null));
    }
}
