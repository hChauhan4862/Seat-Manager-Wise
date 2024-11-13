package wise.controllers.web.university;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.entities.ZoneEntity;
import wise.models.request.university.ZonePatchRequest;
import wise.models.request.university.ZoneRequest;
import wise.models.response.university.ZoneResponse;
import wise.services.university.ZoneService;
import wise.services.app.ResponseService;
import wise.utils.RequestUtil;
import wise.utils.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(Constant.WEB_BASE_API + "zones")
@Tag(name = "Zone Management", description = "Zone-specific APIs")
public class ZoneController {

    private static final Logger logger = LoggerFactory.getLogger(ZoneController.class);

    private final ZoneService zoneService;
    private final ResponseService responseService;

    public ZoneController(ZoneService zoneService, ResponseService responseService) {
        this.zoneService = zoneService;
        this.responseService = responseService;
    }

    @PostMapping
    @Operation(summary = "Create a new zone")
    public ResponseEntity<ApiResponseModel> createZone(@Valid @RequestBody ZoneRequest zoneRequest) {
        ZoneEntity zoneEntity = RequestUtil.NewPatchDataModifier(ZoneEntity.class, zoneRequest);
        ZoneEntity createdZone = zoneService.createZone(zoneEntity);
        ZoneResponse response = RequestUtil.NewPatchDataModifier(ZoneResponse.class, createdZone);
        logger.info("Zone created successfully: Code={}", createdZone.getZoneCode());
        return ResponseEntity.ok(responseService.successResponse("zone.created", response));
    }

    @GetMapping
    @Operation(summary = "Get all zones")
    public ResponseEntity<ApiResponseModel> getAllZones(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(responseService.successResponse(zoneService.getAllZones(pageable)));
    }

    @GetMapping("/{zoneCode}")
    @Operation(summary = "Get a zone by code")
    public ResponseEntity<ApiResponseModel> getZoneByCode(@PathVariable int zoneCode) {
        return ResponseEntity.ok(responseService.successResponse(zoneService.getZoneByCode(zoneCode)));
    }

    @PutMapping("/{zoneCode}")
    @Operation(summary = "Update a zone by code")
    public ResponseEntity<ApiResponseModel> updateZone(@PathVariable int zoneCode,
            @Valid @RequestBody ZoneRequest zoneRequest) {
        ZoneEntity zoneEntity = RequestUtil.NewPatchDataModifier(ZoneEntity.class, zoneRequest);
        zoneEntity.setZoneCode(zoneCode); // Set the zone code for update
        ZoneEntity updatedZone = zoneService.updateZone(zoneEntity);
        ZoneResponse response = RequestUtil.NewPatchDataModifier(ZoneResponse.class, updatedZone);
        logger.info("Zone updated successfully: Code={}", zoneCode);
        return ResponseEntity.ok(responseService.successResponse("zone.updated", response));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch update a zone by code")
    public ResponseEntity<ApiResponseModel> patchUpdateZone(@PathVariable int id,
            @Valid @RequestBody ZonePatchRequest zonePatchRequest) {
        ZoneEntity existingZone = zoneService.getZoneByCode(id);
        RequestUtil.PatchDataModifier(existingZone, zonePatchRequest, true);
        zoneService.updateZone(existingZone);
        logger.info("Zone patched successfully: ID={}", id);
        return ResponseEntity.ok(responseService.successResponse("zone.updated", null));
    }

    @DeleteMapping("/{zoneCode}")
    @Operation(summary = "Delete a zone by code")
    public ResponseEntity<ApiResponseModel> deleteZone(@PathVariable int zoneCode) {
        zoneService.deleteZone(zoneCode);
        logger.info("Zone deleted successfully: Code={}", zoneCode);
        return ResponseEntity.ok(responseService.successResponse("zone.deleted", null));
    }
}
