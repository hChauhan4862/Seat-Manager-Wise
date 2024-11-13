package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.RoomEntity;
import wise.models.request.university.RoomPatchRequest;
import wise.models.request.university.RoomRequest;
import wise.models.response.university.RoomResponse;
import wise.services.university.RoomService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "rooms")
@Tag(name = "Room Management", description = "Room-specific APIs")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;
    private final ResponseService responseService;

    public RoomController(RoomService roomService, ResponseService responseService) {
        this.roomService = roomService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new room")
    public ResponseEntity<ApiResponseModel> createRoom(@Valid @RequestBody RoomRequest roomModel) {
        RoomEntity roomEntity = RequestUtil.NewPatchDataModifier(RoomEntity.class, roomModel);
        RoomEntity createdRoom = roomService.createRoom(roomEntity);
        RoomResponse response = RequestUtil.NewPatchDataModifier(RoomResponse.class, createdRoom);
        logger.info("Room created successfully: Code={}", createdRoom.getRoomCode());
        return ResponseEntity.ok(responseService.successResponse("rooms.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all rooms")
    public ResponseEntity<ApiResponseModel> getAllRooms(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(roomService.getAllRooms(pageable)));
    }

    @GetMapping("/{roomCode}")
    @Operation(summary = "Get a room by code")
    public ResponseEntity<ApiResponseModel> getRoomByCode(@PathVariable Integer roomCode) {
        return ResponseEntity.ok(responseService.successResponse(roomService.getRoomByCode(roomCode)));
    }

    @GetMapping("/by-name/{roomName}")
    @Operation(summary = "Get a room by name")
    public ResponseEntity<ApiResponseModel> getRoomByName(@PathVariable String roomName) {
        return ResponseEntity.ok(responseService.successResponse(roomService.getRoomByName(roomName)));
    }

    @PutMapping("/{roomCode}")
    @Operation(summary = "Update a room by code")
    public ResponseEntity<ApiResponseModel> updateRoom(@PathVariable Integer roomCode,
            @Valid @RequestBody RoomRequest roomModel) {
        RoomEntity roomEntity = RequestUtil.NewPatchDataModifier(RoomEntity.class, roomModel);
        roomEntity.setRoomCode(roomCode); // Set the room code for update
        RoomEntity updatedRoom = roomService.updateRoom(roomEntity);
        RoomResponse response = RequestUtil.NewPatchDataModifier(RoomResponse.class, updatedRoom);
        logger.info("Room updated successfully: Code={}", roomCode);
        return ResponseEntity.ok(responseService.successResponse("rooms.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a room by code")
    public ResponseEntity<ApiResponseModel> patchUpdateRoom(@PathVariable int id,
            @Valid @RequestBody RoomPatchRequest roomPatchRequest) {
        RoomEntity existingRoom = roomService.getRoomByCode(id);
        RequestUtil.PatchDataModifier(existingRoom, roomPatchRequest, true);
        roomService.updateRoom(existingRoom);
        logger.info("Room patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("room.updated", null));
    }

    @DeleteMapping("/{roomCode}")
    @Operation(summary = "Delete a room by code")
    public ResponseEntity<ApiResponseModel> deleteRoom(@PathVariable Integer roomCode) {
        roomService.deleteRoom(roomCode);
        logger.info("Room deleted successfully: Code={}", roomCode);
        return ResponseEntity.ok(responseService.successResponse("rooms.deleted", null));
    }
}
