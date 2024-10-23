package wise.controllers.web.user_management;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.*;
import wise.models.user_management.PositionsModel;

import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wise.services.app.ResponseService;
import wise.services.user_management.PositionsService;
import wise.utils.RequestUtil;

@RestController
@RequestMapping("/web/users_positions")
@Tag(name = "User Positions", description = "User Positions-specific APIs")
public class PositionController {

    private static final Logger logger = LoggerFactory.getLogger(PositionController.class);

    private final PositionsService posService;
    private final ResponseService responseService;

    public PositionController(PositionsService posService, ResponseService responseService) {
        this.posService = posService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new position")
    public ResponseEntity<ApiResponseModel> createPosition(@Valid @RequestBody PositionsModel posDto) {
        
        PositionEntity posEntity = RequestUtil.NewPatchDataModifier(PositionEntity.class, posDto);
        posService.create(posEntity);

        logger.info("Position created successfully: ID={}", posEntity.getPosCode());
        return ResponseEntity.ok(responseService.successResponse("positions.created", null));
    }

    @GetMapping
    @Operation(summary = "Get all positions")
    public ResponseEntity<ApiResponseModel> getAllPositions(@PageableDefault Pageable pageable) {
        Page<PositionEntity> positions = posService.getAllPositions(pageable);

        logger.info("Positions retrieved successfully");
        return ResponseEntity.ok(responseService.successResponse(positions));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get a position by code")
    public ResponseEntity<ApiResponseModel> getPositionById(@PathVariable String code) {
        PositionEntity position = posService.getByCode(code);

        logger.info("Position retrieved successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse(position));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update a position by code")
    public ResponseEntity<ApiResponseModel> updatePosition(@PathVariable String code, @Valid @RequestBody PositionsModel posDto) {

        PositionEntity existingPosition = posService.getByCode(code); // Fetch existing position
        RequestUtil.PatchDataModifier(existingPosition, posDto); // Apply the changes using PatchDataModifier
        posService.updatePosition(existingPosition); // Save the updated entity

        logger.info("Position updated successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("positions.updated", null));
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Delete a position by code")
    public ResponseEntity<ApiResponseModel> deletePosition(@PathVariable String code) {

        posService.deletePosition(code);

        logger.info("Position deleted successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("positions.deleted", null));
    }
}
