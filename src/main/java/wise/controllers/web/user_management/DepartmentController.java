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
import wise.models.request.user_management.DepartmentsModel;

import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wise.services.app.ResponseService;
import wise.services.user_management.DepartmentsService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "users_departments")
@Tag(name = "User Departments", description = "User Departments-specific APIs")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentsService deptService;
    private final ResponseService responseService;

    public DepartmentController(DepartmentsService deptService, ResponseService responseService) {
        this.deptService = deptService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new department")
    public ResponseEntity<ApiResponseModel> createDepartment(@Valid @RequestBody DepartmentsModel deptDto) {
        
        DepartmentEntity deptEntity = RequestUtil.NewPatchDataModifier(DepartmentEntity.class, deptDto);
        deptService.create(deptEntity);

        logger.info("Department created successfully: ID={}", deptEntity.getDeptCode());
        return ResponseEntity.ok(responseService.successResponse("departments.created", null));
    }

    @GetMapping
    @Operation(summary = "Get all departments")
    public ResponseEntity<ApiResponseModel> getAllDepartments(@PageableDefault Pageable pageable) {
        Page<DepartmentEntity> departments = deptService.getAllDepartments(pageable);

        logger.info("Departments retrieved successfully");
        return ResponseEntity.ok(responseService.successResponse(departments));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get a department by code")
    public ResponseEntity<ApiResponseModel> getDepartmentById(@PathVariable String code) {
        DepartmentEntity department = deptService.getByCode(code);

        logger.info("Department retrieved successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse(department));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update a department by code")
    public ResponseEntity<ApiResponseModel> updateDepartment(@PathVariable String code, @Valid @RequestBody DepartmentsModel deptDto) {

        DepartmentEntity existingDepartment = deptService.getByCode(code); // Fetch existing department
        RequestUtil.PatchDataModifier(existingDepartment, deptDto); // Apply the changes using PatchDataModifier
        deptService.updateDepartment(existingDepartment); // Save the updated entity

        logger.info("Department updated successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("departments.updated", null));
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Delete a department by code")
    public ResponseEntity<ApiResponseModel> deleteDepartment(@PathVariable String code) {

        deptService.deleteDepartment(code);

        logger.info("Department deleted successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("departments.deleted", null));
    }
}
