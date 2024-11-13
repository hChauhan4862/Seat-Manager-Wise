package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.RoomConfigEntity;
import wise.models.request.university.RoomConfigPatchRequest;
import wise.services.university.RoomConfigService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "room-configs")
@Tag(name = "Room Config Management", description = "Room Configuration-specific APIs")
public class RoomConfigController {

    private static final Logger logger = LoggerFactory.getLogger(RoomConfigController.class);

    private final RoomConfigService roomConfigService;
    private final ResponseService responseService;

    public RoomConfigController(RoomConfigService roomConfigService, ResponseService responseService) {
        this.roomConfigService = roomConfigService;
        this.responseService = responseService;
    }

   
    @GetMapping
    @Operation(summary = "Get all room configurations")
    public ResponseEntity<ApiResponseModel> getAllRoomConfigs(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(roomConfigService.getAllRoomConfigs(pageable)));
    }

    @GetMapping("/{roomCode}")
    @Operation(summary = "Get a room configuration by room code")
    public ResponseEntity<ApiResponseModel> getRoomConfigByRoomCode(@PathVariable int roomCode) {
        return ResponseEntity.ok(responseService.successResponse(roomConfigService.getRoomConfigByRoomCode(roomCode)));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a room configuration by code")
    public ResponseEntity<ApiResponseModel> patchUpdateRoomConfig(@PathVariable int id,
            @Valid @RequestBody RoomConfigPatchRequest roomConfigPatchRequest) {
        RoomConfigEntity existingRoomConfig = roomConfigService.getRoomConfigByRoomCode(id);
        RequestUtil.PatchDataModifier(existingRoomConfig, roomConfigPatchRequest, true);
        roomConfigService.updateRoomConfig(existingRoomConfig);
        logger.info("Room configuration patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("roomConfig.updated", null));
    }

    @DeleteMapping("/{roomCode}")
    @Operation(summary = "Delete a room configuration by room code")
    public ResponseEntity<ApiResponseModel> deleteRoomConfig(@PathVariable int roomCode) {
        roomConfigService.deleteRoomConfig(roomCode);
        logger.info("Room configuration deleted successfully: Code={}", roomCode);
        return ResponseEntity.ok(responseService.successResponse("roomConfig.deleted", null));
    }
}
