package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.FloorEntity;
import wise.models.request.university.FloorPatchRequest;
import wise.models.request.university.FloorRequest;
import wise.models.response.university.FloorResponse;
import wise.services.university.FloorService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "floors")
@Tag(name = "Floor Management", description = "Floor-specific APIs")
public class FloorController {

    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    private final FloorService floorService;
    private final ResponseService responseService;

    public FloorController(FloorService floorService, ResponseService responseService) {
        this.floorService = floorService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new floor")
    public ResponseEntity<ApiResponseModel> createFloor(@Valid @RequestBody FloorRequest floorModel) {
        FloorEntity floorEntity = RequestUtil.NewPatchDataModifier(FloorEntity.class, floorModel);
        FloorEntity createdFloor = floorService.createFloor(floorEntity);
        FloorResponse response = RequestUtil.NewPatchDataModifier(FloorResponse.class, createdFloor);
        logger.info("Floor created successfully: Code={}", createdFloor.getFloorCode());
        return ResponseEntity.ok(responseService.successResponse("floors.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all floors")
    public ResponseEntity<ApiResponseModel> getAllFloors(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(floorService.getAllFloors(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a floor by ID")
    public ResponseEntity<ApiResponseModel> getFloorById(@PathVariable Integer id) {
        return ResponseEntity.ok(responseService.successResponse(floorService.getFloorById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a floor by ID")
    public ResponseEntity<ApiResponseModel> updateFloor(@PathVariable Integer id,
            @Valid @RequestBody FloorRequest floorModel) {
        FloorEntity updatedFloor = floorService
                .updateFloor(RequestUtil.NewPatchDataModifier(FloorEntity.class, floorModel));
        FloorResponse response = RequestUtil.NewPatchDataModifier(FloorResponse.class, updatedFloor);
        logger.info("Floor updated successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("floors.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a floor by code")
    public ResponseEntity<ApiResponseModel> patchUpdateFloor(@PathVariable int id,
            @Valid @RequestBody FloorPatchRequest floorPatchDto) {
        FloorEntity existingFloor = floorService.getFloorById(id);
        RequestUtil.PatchDataModifier(existingFloor, floorPatchDto, true);
        floorService.updateFloor(existingFloor);
        logger.info("Floor patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("floor.updated", null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a floor by ID")
    public ResponseEntity<ApiResponseModel> deleteFloor(@PathVariable Integer id) {
        floorService.deleteFloor(id);
        logger.info("Floor deleted successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("floors.deleted", null));
    }
}
