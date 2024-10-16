package wise.controllers.web.app;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import wise.common.ApiResponseModel;
import wise.dtos.config.SysConfigDto;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import wise.services.app.ConfigurationService;
import wise.services.app.ResponseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;

import java.util.List;

@RestController
@RequestMapping("/web/config")
@Tag(name = "Config", description = "System Configuration APIs")
public class ConfigController {

    // private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    private final ConfigurationService configurationService;
    private final ResponseService responseService;

    public ConfigController(ConfigurationService configurationService, ResponseService responseService) {
        this.configurationService = configurationService;
        this.responseService = responseService;
    }

    @Autowired
    private RefreshScope refreshScope;

    @Operation(summary = "System properties configuration APIs")
    @PostMapping("system_properties_update")
    public ResponseEntity<ApiResponseModel> updateConfig(@Valid @RequestBody List<SysConfigDto> sysConfigDto) {
        for (SysConfigDto config : sysConfigDto) {
            String key = config.getKey();
            String value = config.getValue();

            configurationService.updateProperty(key, value);
        }

        // Trigger refresh of the configuration by calling refresh scope
        refreshScope.refreshAll();

        // Optionally restart the application context (depending on your need)
        // restartEndpoint.restart();

        return ResponseEntity.ok(responseService.successResponse("sysproperties.updated", null));
    }
}
