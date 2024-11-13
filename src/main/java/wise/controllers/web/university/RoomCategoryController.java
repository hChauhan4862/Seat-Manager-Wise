package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.RoomCategoryEntity;
import wise.models.request.university.RoomCategoryRequest;
import wise.models.response.university.RoomCategoryResponse;
import wise.services.university.RoomCategoryService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "room-categories")
@Tag(name = "Room Category Management", description = "Room Category-specific APIs")
public class RoomCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(RoomCategoryController.class);

    private final RoomCategoryService roomCategoryService;
    private final ResponseService responseService;

    public RoomCategoryController(RoomCategoryService roomCategoryService, ResponseService responseService) {
        this.roomCategoryService = roomCategoryService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new room category")
    public ResponseEntity<ApiResponseModel> createRoomCategory(@Valid @RequestBody RoomCategoryRequest roomCategoryModel) {
        RoomCategoryEntity roomCategoryEntity = RequestUtil.NewPatchDataModifier(RoomCategoryEntity.class, roomCategoryModel);
        RoomCategoryEntity createdRoomCategory = roomCategoryService.createRoomCategory(roomCategoryEntity);
        RoomCategoryResponse response = RequestUtil.NewPatchDataModifier(RoomCategoryResponse.class, createdRoomCategory);
        logger.info("Room category created successfully: Code={}", createdRoomCategory.getRoomCatCode());
        return ResponseEntity.ok(responseService.successResponse("roomCategories.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all room categories")
    public ResponseEntity<ApiResponseModel> getAllRoomCategories(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(roomCategoryService.getAllRoomCategories(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a room category by ID")
    public ResponseEntity<ApiResponseModel> getRoomCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(responseService.successResponse(roomCategoryService.getRoomCategoryById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a room category by ID")
    public ResponseEntity<ApiResponseModel> updateRoomCategory(@PathVariable Integer id, @Valid @RequestBody RoomCategoryRequest roomCategoryModel) {
        RoomCategoryEntity updatedRoomCategory = roomCategoryService.updateRoomCategory(RequestUtil.NewPatchDataModifier(RoomCategoryEntity.class, roomCategoryModel));
        RoomCategoryResponse response = RequestUtil.NewPatchDataModifier(RoomCategoryResponse.class, updatedRoomCategory);
        logger.info("Room category updated successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("roomCategories.updated", response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a room category by ID")
    public ResponseEntity<ApiResponseModel> deleteRoomCategory(@PathVariable Integer id) {
        roomCategoryService.deleteRoomCategory(id);
        logger.info("Room category deleted successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("roomCategories.deleted", null));
    }
}
