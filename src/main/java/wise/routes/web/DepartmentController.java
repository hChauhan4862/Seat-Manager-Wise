package wise.routes.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.dto.ApiResponseModel;
import wise.dto.departments.CreateDepartmentsDto;
import wise.dto.departments.UpdateDepartmentsDto;


import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wise.models.*;
import wise.services.*;
import java.util.Optional;
import wise.utils.helper.RequestUtil;;

@RestController
@RequestMapping("/web/departments")
@Tag(name = "Department", description = "Department-specific APIs")
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
    public ResponseEntity<ApiResponseModel> createDepartment(@Valid @RequestBody CreateDepartmentsDto deptDto) {
        
        DepartmentEntity deptEntity = new DepartmentEntity();
        RequestUtil.PatchDataModifier(deptEntity, deptDto);
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
        Optional<DepartmentEntity> department = deptService.getByCode(code);
        if (!department.isPresent()) {
            logger.warn("Department not found: ID={}", code);
            return new ResponseEntity<>(responseService.errorResponse("departments.notFound", null), HttpStatus.NOT_FOUND);
        }

        logger.info("Department retrieved successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse(department.get()));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update a department by code")
    public ResponseEntity<ApiResponseModel> updateDepartment(@PathVariable String code, @Valid @RequestBody UpdateDepartmentsDto deptDto) {
        DepartmentEntity deptEntity = new DepartmentEntity();
        RequestUtil.PatchDataModifier(deptEntity, deptDto);
        deptEntity.setDeptCode(code);

        DepartmentEntity savedEntity = deptService.updateDepartment(code, deptEntity);
        if (savedEntity == null) {
            logger.warn("Department not found: ID={}", code);
            return new ResponseEntity<ApiResponseModel>(responseService.errorResponse("departments.notFound", null), HttpStatus.NOT_FOUND);
        }

        logger.info("Department updated successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("departments.updated", null));
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Delete a department by code")
    public ResponseEntity<ApiResponseModel> deleteDepartment(@PathVariable String code) {

        Optional<DepartmentEntity> department = deptService.getByCode(code);
        if (!department.isPresent()) {
            logger.warn("Department not found: ID={}", code);
            return new ResponseEntity<>(responseService.errorResponse("departments.notFound", null), HttpStatus.NOT_FOUND);
        }

        deptService.deleteDepartment(code);

        logger.info("Department deleted successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("departments.deleted", null));
    }
}
