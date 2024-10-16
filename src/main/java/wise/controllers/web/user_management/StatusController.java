package wise.controllers.web.user_management;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.dtos.user_management.StatusesDto;
import wise.entities.*;

import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wise.services.app.ResponseService;
import wise.services.user_management.StatusesService;
import wise.utils.RequestUtil;

@RestController
@RequestMapping("/web/users_status")
@Tag(name = "User Management", description = "User Management-specific APIs")
public class StatusController {

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    private final StatusesService statusService;
    private final ResponseService responseService;

    public StatusController(StatusesService statusService, ResponseService responseService) {
        this.statusService = statusService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new status")
    public ResponseEntity<ApiResponseModel> createStatus(@Valid @RequestBody StatusesDto statusDto) {
        
        StatusEntity statusEntity = RequestUtil.NewPatchDataModifier(StatusEntity.class, statusDto);
        statusService.create(statusEntity);

        logger.info("Status created successfully: ID={}", statusEntity.getStatusCode());
        return ResponseEntity.ok(responseService.successResponse("statuses.created", null));
    }

    @GetMapping
    @Operation(summary = "Get all statuses")
    public ResponseEntity<ApiResponseModel> getAllStatuses(@PageableDefault Pageable pageable) {
        Page<StatusEntity> statuses = statusService.getAllStatuses(pageable);

        logger.info("Statuses retrieved successfully");
        return ResponseEntity.ok(responseService.successResponse(statuses));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get a status by code")
    public ResponseEntity<ApiResponseModel> getStatusById(@PathVariable String code) {
        StatusEntity status = statusService.getByCode(code);

        logger.info("Status retrieved successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse(status));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update a status by code")
    public ResponseEntity<ApiResponseModel> updateStatus(@PathVariable String code, @Valid @RequestBody StatusesDto statusDto) {

        StatusEntity existingStatus = statusService.getByCode(code); // Fetch existing status
        RequestUtil.PatchDataModifier(existingStatus, statusDto); // Apply the changes using PatchDataModifier
        statusService.updateStatus(existingStatus); // Save the updated entity

        logger.info("Status updated successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("statuses.updated", null));
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Delete a status by code")
    public ResponseEntity<ApiResponseModel> deleteStatus(@PathVariable String code) {

        statusService.deleteStatus(code);

        logger.info("Status deleted successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("statuses.deleted", null));
    }
}
