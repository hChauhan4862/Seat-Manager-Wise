package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.ScheduleTypeEntity;
import wise.models.request.university.ScheduleTypeRequest;
import wise.models.response.university.ScheduleTypeResponse;
import wise.services.university.ScheduleTypeService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "schedule-types")
@Tag(name = "Schedule Type Management", description = "Schedule Type-specific APIs")
public class ScheduleTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTypeController.class);

    private final ScheduleTypeService scheduleTypeService;
    private final ResponseService responseService;

    public ScheduleTypeController(ScheduleTypeService scheduleTypeService, ResponseService responseService) {
        this.scheduleTypeService = scheduleTypeService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new schedule type")
    public ResponseEntity<ApiResponseModel> createScheduleType(@Valid @RequestBody ScheduleTypeRequest scheduleTypeRequest) {
        ScheduleTypeEntity scheduleTypeEntity = RequestUtil.NewPatchDataModifier(ScheduleTypeEntity.class, scheduleTypeRequest);
        ScheduleTypeEntity createdScheduleType = scheduleTypeService.createScheduleType(scheduleTypeEntity);
        ScheduleTypeResponse response = RequestUtil.NewPatchDataModifier(ScheduleTypeResponse.class, createdScheduleType);
        logger.info("Schedule type created successfully: Code={}", createdScheduleType.getScheduleTypeCode());
        return ResponseEntity.ok(responseService.successResponse("scheduleType.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all schedule types")
    public ResponseEntity<ApiResponseModel> getAllScheduleTypes(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(scheduleTypeService.getAllScheduleTypes(pageable)));
    }

    @GetMapping("/{scheduleTypeCode}")
    @Operation(summary = "Get a schedule type by code")
    public ResponseEntity<ApiResponseModel> getScheduleTypeByCode(@PathVariable int scheduleTypeCode) {
        return ResponseEntity.ok(responseService.successResponse(scheduleTypeService.getScheduleTypeByCode(scheduleTypeCode)));
    }

    @PutMapping("/{scheduleTypeCode}")
    @Operation(summary = "Update a schedule type by code")
    public ResponseEntity<ApiResponseModel> updateScheduleType(@PathVariable int scheduleTypeCode, @Valid @RequestBody ScheduleTypeRequest scheduleTypeRequest) {
        ScheduleTypeEntity scheduleTypeEntity = RequestUtil.NewPatchDataModifier(ScheduleTypeEntity.class, scheduleTypeRequest);
        scheduleTypeEntity.setScheduleTypeCode(scheduleTypeCode); // Set the schedule type code for update
        ScheduleTypeEntity updatedScheduleType = scheduleTypeService.updateScheduleType(scheduleTypeEntity);
        ScheduleTypeResponse response = RequestUtil.NewPatchDataModifier(ScheduleTypeResponse.class, updatedScheduleType);
        logger.info("Schedule type updated successfully: Code={}", scheduleTypeCode);
        return ResponseEntity.ok(responseService.successResponse("scheduleType.updated", response));
    }

    @DeleteMapping("/{scheduleTypeCode}")
    @Operation(summary = "Delete a schedule type by code")
    public ResponseEntity<ApiResponseModel> deleteScheduleType(@PathVariable int scheduleTypeCode) {
        scheduleTypeService.deleteScheduleType(scheduleTypeCode);
        logger.info("Schedule type deleted successfully: Code={}", scheduleTypeCode);
        return ResponseEntity.ok(responseService.successResponse("scheduleType.deleted", null));
    }
}
