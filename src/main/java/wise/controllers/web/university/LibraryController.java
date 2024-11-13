package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.LibraryEntity;
import wise.models.request.university.LibraryPatchRequest;
import wise.models.request.university.LibraryRequest;
import wise.models.response.university.LibraryResponse;
import wise.services.university.LibraryService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "libraries")
@Tag(name = "Library Management", description = "Library-specific APIs")
public class LibraryController {

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private final LibraryService libraryService;
    private final ResponseService responseService;

    public LibraryController(LibraryService libraryService, ResponseService responseService) {
        this.libraryService = libraryService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new library")
    public ResponseEntity<ApiResponseModel> createLibrary(@Valid @RequestBody LibraryRequest libraryModel) {
        LibraryEntity libraryEntity = RequestUtil.NewPatchDataModifier(LibraryEntity.class, libraryModel);
        System.out.println(libraryEntity.toString());
        LibraryEntity createdLibrary = libraryService.createLibrary(libraryEntity);
        LibraryResponse response = RequestUtil.NewPatchDataModifier(LibraryResponse.class, createdLibrary);
        logger.info("Library created successfully: Code={}", createdLibrary.getLibCode());
        return ResponseEntity.ok(responseService.successResponse("libraries.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all libraries")
    public ResponseEntity<ApiResponseModel> getAllLibraries(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(libraryService.getAllLibraries(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a library by ID")
    public ResponseEntity<ApiResponseModel> getLibraryById(@PathVariable Integer id) {
        return ResponseEntity.ok(responseService.successResponse(libraryService.getLibraryById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a library by ID")
    public ResponseEntity<ApiResponseModel> updateLibrary(@PathVariable Integer id,
            @Valid @RequestBody LibraryRequest libraryModel) {
        LibraryEntity updatedLibrary = libraryService.updateLibrary(RequestUtil.NewPatchDataModifier(LibraryEntity.class, libraryModel));
        LibraryResponse response = RequestUtil.NewPatchDataModifier(LibraryResponse.class, updatedLibrary);
        logger.info("Library updated successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("libraries.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a library by code")
    public ResponseEntity<ApiResponseModel> patchUpdateLibrary(@PathVariable int id,
            @Valid @RequestBody LibraryPatchRequest libraryPatchDto) {
        LibraryEntity existingLibrary = libraryService.getLibraryById(id);
        RequestUtil.PatchDataModifier(existingLibrary, libraryPatchDto, true);
        libraryService.updateLibrary(existingLibrary);
        logger.info("Library patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("library.updated", null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a library by ID")
    public ResponseEntity<ApiResponseModel> deleteLibrary(@PathVariable Integer id) {
        libraryService.deleteLibrary(id);
        logger.info("Library deleted successfully: Code={}", id);
        return ResponseEntity.ok(responseService.successResponse("libraries.deleted", null));
    }
}
