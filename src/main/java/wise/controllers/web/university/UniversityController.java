package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.dtos.university.CreateUniversityDto;
import wise.dtos.university.UpdateUniversityDto;
import wise.entities.UniversityEntity;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wise.services.app.ResponseService;
import wise.services.university.UniversityService;
import wise.utils.RequestUtil;

import java.util.Optional;

@RestController
@RequestMapping("/web/universities")
@Tag(name = "University", description = "University-specific APIs")
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
    public ResponseEntity<ApiResponseModel> createUniversity(@Valid @RequestBody CreateUniversityDto universityDto) {

        UniversityEntity universityEntity = new UniversityEntity();
        RequestUtil.PatchDataModifier(universityEntity, universityDto);
        universityService.create(universityEntity);

        logger.info("University created successfully: ID={}", universityEntity.getUnivCode());
        return ResponseEntity.ok(responseService.successResponse("universities.created", null));
    }

    @GetMapping
    @Operation(summary = "Get all universities")
    public ResponseEntity<ApiResponseModel> getAllUniversities(@PageableDefault Pageable pageable) {
        Page<UniversityEntity> universities = universityService.getAllUniversities(pageable);
        logger.info("Universities retrieved successfully");
        return ResponseEntity.ok(responseService.successResponse(universities));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get a university by code")
    public ResponseEntity<ApiResponseModel> getUniversityById(@PathVariable Integer code) {
        Optional<UniversityEntity> university = universityService.getByCode(code);
        if (!university.isPresent()) {
            logger.warn("University not found: ID={}", code);
            return new ResponseEntity<>(responseService.errorResponse("universities.notFound", null), HttpStatus.NOT_FOUND);
        }

        logger.info("University retrieved successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse(university.get()));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update a university by code")
    public ResponseEntity<ApiResponseModel> updateUniversity(@PathVariable Integer code, @Valid @RequestBody UpdateUniversityDto universityDto) {
        UniversityEntity universityEntity = new UniversityEntity();
        RequestUtil.PatchDataModifier(universityEntity, universityDto);
        universityEntity.setUnivCode(code);

        UniversityEntity savedEntity = universityService.updateUniversity(code, universityEntity);
        if (savedEntity == null) {
            logger.warn("University not found: ID={}", code);
            return new ResponseEntity<>(responseService.errorResponse("universities.notFound", null), HttpStatus.NOT_FOUND);
        }

        logger.info("University updated successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("universities.updated", null));
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Delete a university by code")
    public ResponseEntity<ApiResponseModel> deleteUniversity(@PathVariable Integer code) {

        Optional<UniversityEntity> university = universityService.getByCode(code);
        if (!university.isPresent()) {
            logger.warn("University not found: ID={}", code);
            return new ResponseEntity<>(responseService.errorResponse("universities.notFound", null), HttpStatus.NOT_FOUND);
        }

        universityService.deleteUniversity(code);

        logger.info("University deleted successfully: ID={}", code);
        return ResponseEntity.ok(responseService.successResponse("universities.deleted", null));
    }
}
