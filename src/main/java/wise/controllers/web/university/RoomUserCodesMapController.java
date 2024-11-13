package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.RoomUserCodesMapEntity;
import wise.models.request.university.RoomUserCodesMapPatchRequest;
import wise.models.request.university.RoomUserCodesMapRequest;
import wise.models.response.university.RoomUserCodesMapResponse;
import wise.services.university.RoomUserCodesMapService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "roomUserCodesMaps")
@Tag(name = "Room User Codes Map Management", description = "APIs for managing room-user codes mappings")
public class RoomUserCodesMapController {

    private static final Logger logger = LoggerFactory.getLogger(RoomUserCodesMapController.class);

    private final RoomUserCodesMapService roomUserCodesMapService;
    private final ResponseService responseService;

    public RoomUserCodesMapController(RoomUserCodesMapService roomUserCodesMapService, ResponseService responseService) {
        this.roomUserCodesMapService = roomUserCodesMapService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new Room User Code Map")
    public ResponseEntity<ApiResponseModel> createRoomUserCodesMap(@Valid @RequestBody RoomUserCodesMapRequest request) {
        RoomUserCodesMapEntity roomUserCodesMapEntity = RequestUtil.NewPatchDataModifier(RoomUserCodesMapEntity.class, request);
        RoomUserCodesMapEntity createdMap = roomUserCodesMapService.createRoomUserCodesMap(roomUserCodesMapEntity);
        RoomUserCodesMapResponse response = RequestUtil.NewPatchDataModifier(RoomUserCodesMapResponse.class, createdMap);
        logger.info("Room User Codes Map created successfully: Seq={}", createdMap.getSeq());
        return ResponseEntity.ok(responseService.successResponse("roomUserCodeMap.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all Room User Codes Maps with pagination")
    public ResponseEntity<ApiResponseModel> getAllRoomUserCodesMaps(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(roomUserCodesMapService.getAllRoomUserCodesMaps(pageable)));
    }

    @GetMapping("/{seq}")
    @Operation(summary = "Get Room User Code Map by seq")
    public ResponseEntity<ApiResponseModel> getRoomUserCodesMapBySeq(@PathVariable int seq) {
        RoomUserCodesMapEntity roomUserCodesMapEntity = roomUserCodesMapService.getRoomUserCodesMapBySeq(seq);
        RoomUserCodesMapResponse response = RequestUtil.NewPatchDataModifier(RoomUserCodesMapResponse.class, roomUserCodesMapEntity);
        return ResponseEntity.ok(responseService.successResponse("roomUserCodeMap.found", response));
    }

    @PutMapping("/{seq}")
    @Operation(summary = "Update Room User Code Map by seq")
    public ResponseEntity<ApiResponseModel> updateRoomUserCodesMap(@PathVariable int seq, @Valid @RequestBody RoomUserCodesMapRequest request) {
        RoomUserCodesMapEntity roomUserCodesMapEntity = RequestUtil.NewPatchDataModifier(RoomUserCodesMapEntity.class, request);
        RoomUserCodesMapEntity updatedMap = roomUserCodesMapService.updateRoomUserCodesMap(                     roomUserCodesMapEntity);
        RoomUserCodesMapResponse response = RequestUtil.NewPatchDataModifier(RoomUserCodesMapResponse.class, updatedMap);
        logger.info("Room User Codes Map updated successfully: Seq={}", seq);
        return ResponseEntity.ok(responseService.successResponse("roomUserCodeMap.updated", response));
    }

    @PatchMapping("/{id}")
@Operation(summary = "Patch update a room user codes map by code")
public ResponseEntity<ApiResponseModel> patchUpdateRoomUserCodesMap(@PathVariable int id, @Valid @RequestBody RoomUserCodesMapPatchRequest roomUserCodesMapPatchRequest) {
    RoomUserCodesMapEntity existingRoomUserCodesMap = roomUserCodesMapService.getRoomUserCodesMapBySeq(id);
    RequestUtil.PatchDataModifier(existingRoomUserCodesMap, roomUserCodesMapPatchRequest, true);
    roomUserCodesMapService.updateRoomUserCodesMap(existingRoomUserCodesMap);
    logger.info("RoomUserCodesMap patched successfully: ID={}", id);
    return ResponseEntity.ok(responseService.successResponse("roomUserCodesMap.updated", null));
}


    @DeleteMapping("/{seq}")
    @Operation(summary = "Delete Room User Code Map by seq")
    public ResponseEntity<ApiResponseModel> deleteRoomUserCodesMap(@PathVariable int seq) {
        roomUserCodesMapService.deleteRoomUserCodesMap(seq);
        logger.info("Room User Codes Map deleted successfully: Seq={}", seq);
        return ResponseEntity.ok(responseService.successResponse("roomUserCodeMap.deleted", null));
    }
}
