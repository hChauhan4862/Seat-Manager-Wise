package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.SeatCategoryEntity;
import wise.models.request.university.SeatCategoryRequest;
import wise.models.response.university.SeatCategoryResponse;
import wise.services.university.SeatCategoryService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "seat-categories")
@Tag(name = "Seat Category Management", description = "Seat Category-specific APIs")
public class SeatCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(SeatCategoryController.class);

    private final SeatCategoryService seatCategoryService;
    private final ResponseService responseService;

    public SeatCategoryController(SeatCategoryService seatCategoryService, ResponseService responseService) {
        this.seatCategoryService = seatCategoryService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new seat category")
    public ResponseEntity<ApiResponseModel> createSeatCategory(@Valid @RequestBody SeatCategoryRequest seatCategoryRequest) {
        SeatCategoryEntity seatCategoryEntity = RequestUtil.NewPatchDataModifier(SeatCategoryEntity.class, seatCategoryRequest);
        SeatCategoryEntity createdSeatCategory = seatCategoryService.createSeatCategory(seatCategoryEntity);
        SeatCategoryResponse response = RequestUtil.NewPatchDataModifier(SeatCategoryResponse.class, createdSeatCategory);
        logger.info("Seat category created successfully: Code={}", createdSeatCategory.getSeatCatCode());
        return ResponseEntity.ok(responseService.successResponse("seatCategory.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all seat categories")
    public ResponseEntity<ApiResponseModel> getAllSeatCategories(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(seatCategoryService.getAllSeatCategories(pageable)));
    }

    @GetMapping("/{seatCatCode}")
    @Operation(summary = "Get a seat category by code")
    public ResponseEntity<ApiResponseModel> getSeatCategoryByCode(@PathVariable int seatCatCode) {
        return ResponseEntity.ok(responseService.successResponse(seatCategoryService.getSeatCategoryByCode(seatCatCode)));
    }

    @PutMapping("/{seatCatCode}")
    @Operation(summary = "Update a seat category by code")
    public ResponseEntity<ApiResponseModel> updateSeatCategory(@PathVariable int seatCatCode, @Valid @RequestBody SeatCategoryRequest seatCategoryRequest) {
        SeatCategoryEntity seatCategoryEntity = RequestUtil.NewPatchDataModifier(SeatCategoryEntity.class, seatCategoryRequest);
        SeatCategoryEntity updatedSeatCategory = seatCategoryService.updateSeatCategory(seatCategoryEntity);
        SeatCategoryResponse response = RequestUtil.NewPatchDataModifier(SeatCategoryResponse.class, updatedSeatCategory);
        logger.info("Seat category updated successfully: Code={}", seatCatCode);
        return ResponseEntity.ok(responseService.successResponse("seatCategory.updated", response));
    }

    @DeleteMapping("/{seatCatCode}")
    @Operation(summary = "Delete a seat category by code")
    public ResponseEntity<ApiResponseModel> deleteSeatCategory(@PathVariable int seatCatCode) {
        seatCategoryService.deleteSeatCategory(seatCatCode);
        logger.info("Seat category deleted successfully: Code={}", seatCatCode);
        return ResponseEntity.ok(responseService.successResponse("seatCategory.deleted", null));
    }
}
