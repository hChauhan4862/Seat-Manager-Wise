package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.UniversityEntity;
import wise.models.request.university.UniversityModel;
import wise.models.request.university.UniversityPatchModel;
import wise.services.university.UniversityService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "universities")
@Tag(name = "University Management", description = "University-specific APIs")
public class UniversityController {

    private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

    private final UniversityService universityService;
    private final ResponseService responseService;

    public UniversityController(UniversityService universityService, ResponseService responseService) {
        this.universityService = universityService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new university")
    public ResponseEntity<ApiResponseModel> createUniversity(@Valid @RequestBody UniversityModel universityDto) {
        UniversityEntity universityEntity = RequestUtil.NewPatchDataModifier(UniversityEntity.class, universityDto);
        universityService.createUniversity(universityEntity);
        logger.info("University created successfully: ID={}", universityEntity.getUnivCode());
        return ResponseEntity.ok(responseService.successResponse("university.created", null));
    }

    @GetMapping
    @Operation(summary = "Get all universities")
    public ResponseEntity<ApiResponseModel> getAllUniversities(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(universityService.getAllUniversities(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a university by code")
    public ResponseEntity<ApiResponseModel> getUniversityById(@PathVariable int id) {
        return ResponseEntity.ok(responseService.successResponse(universityService.getUniversityById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a university by code")
    public ResponseEntity<ApiResponseModel> updateUniversity(@PathVariable int id, @Valid @RequestBody UniversityModel universityDto) {
        UniversityEntity existingUniversity = universityService.getUniversityById(id);
        RequestUtil.PatchDataModifier(existingUniversity, universityDto);
        universityService.updateUniversity(existingUniversity);
        logger.info("University updated successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("university.updated", null));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a university by code")
    public ResponseEntity<ApiResponseModel> patchUpdateUniversity(@PathVariable int id, @Valid @RequestBody UniversityPatchModel universityPatchDto) {
        UniversityEntity existingUniversity = universityService.getUniversityById(id);
        RequestUtil.PatchDataModifier(existingUniversity, universityPatchDto, true);
        universityService.updateUniversity(existingUniversity);
        logger.info("University patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("university.updated", null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a university by code")
    public ResponseEntity<ApiResponseModel> deleteUniversity(@PathVariable int id) {
        universityService.deleteUniversity(id);
        logger.info("University deleted successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("university.deleted", null));
    }
}
