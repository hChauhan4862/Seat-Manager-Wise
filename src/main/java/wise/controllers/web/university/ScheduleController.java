package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.ScheduleEntity;
import wise.models.request.university.ScheduleRequest;
import wise.models.response.university.ScheduleResponse;
import wise.services.university.ScheduleService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "schedules")
@Tag(name = "Schedule Management", description = "Schedule-specific APIs")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;
    private final ResponseService responseService;

    public ScheduleController(ScheduleService scheduleService, ResponseService responseService) {
        this.scheduleService = scheduleService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new schedule")
    public ResponseEntity<ApiResponseModel> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        ScheduleEntity scheduleEntity = RequestUtil.NewPatchDataModifier(ScheduleEntity.class, scheduleRequest);
        ScheduleEntity createdSchedule = scheduleService.createSchedule(scheduleEntity);
        ScheduleResponse response = RequestUtil.NewPatchDataModifier(ScheduleResponse.class, createdSchedule);
        logger.info("Schedule created successfully: Code={}", createdSchedule.getScheduleCode());
        return ResponseEntity.ok(responseService.successResponse("schedules.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all schedules")
    public ResponseEntity<ApiResponseModel> getAllSchedules(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(scheduleService.getAllSchedules(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a schedule by ID")
    public ResponseEntity<ApiResponseModel> getScheduleById(@PathVariable Integer id) {
        return ResponseEntity.ok(responseService.successResponse(scheduleService.getScheduleById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a schedule by ID")
    public ResponseEntity<ApiResponseModel> updateSchedule(@PathVariable Integer id,
            @Valid @RequestBody ScheduleRequest scheduleRequest) {
        ScheduleEntity updatedSchedule = scheduleService
                .updateSchedule(RequestUtil.NewPatchDataModifier(ScheduleEntity.class, scheduleRequest));
        ScheduleResponse response = RequestUtil.NewPatchDataModifier(ScheduleResponse.class, updatedSchedule);
        logger.info("Schedule updated successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("schedules.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a schedule by code")
    public ResponseEntity<ApiResponseModel> patchUpdateSchedule(@PathVariable int id,
            @Valid @RequestBody ScheduleRequest schedulePatchRequest) {
        ScheduleEntity existingSchedule = scheduleService.getScheduleById(id);
        RequestUtil.PatchDataModifier(existingSchedule, schedulePatchRequest, true);
        scheduleService.updateSchedule(existingSchedule);
        logger.info("Schedule patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("schedule.updated", null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a schedule by ID")
    public ResponseEntity<ApiResponseModel> deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
        logger.info("Schedule deleted successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("schedules.deleted", null));
    }
}
