package wise.controllers.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import wise.common.ApiResponseModel;
import wise.entities.UserEntity;
import wise.services.app.CommonConfigService;
import wise.services.app.ResponseService;
import wise.services.user_management.UsersService;
import wise.utils.constant.Constant;
import wise.utils.DateTime;
import wise.utils.EncryptionUtil;

@RestController
@Tag(name = "Mobile", description = "Mobile-specific APIs")
@RequestMapping(Constant.MOBILE_BASE_API + "users")
@RequiredArgsConstructor
public class UserRoutes {
    private final ResponseService responseService;
    private final UsersService usersService;
    private final CommonConfigService commonConfigService;
    private final EncryptionUtil encryptionUtil;

    @GetMapping("/myProfile")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<ApiResponseModel> getUserById() {
        String id = "wise01";
        return ResponseEntity.ok(responseService.successResponse(usersService.getUserById(id)));
    }

    @GetMapping("/generateQrCode")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<ApiResponseModel> generateQrCode() {
        String id = "wise01";
        UserEntity user = usersService.getUserById(id);
        int EXP_SECOND = Integer.parseInt(commonConfigService.getConfig("MOBILE_QR_EXP", "120"));
        DateTime DT = new DateTime();
        String EXP_DT = DT.copy().plusSeconds(EXP_SECOND).getDateTimeString();
        String QR_CODE = UUID.randomUUID() + "|" + user.getUserId() + "|" + EXP_DT + "|" + EXP_SECOND;
        
        Map<String, String> RES = new HashMap<>();
        RES.put("QrData", encryptionUtil.encrypt(QR_CODE));
        // RES.put("QrDataPlain", QR_CODE);
        RES.put("GeneratedAt", DT.getDateTimeString());
        RES.put("ExpireAt", EXP_DT);
        return ResponseEntity.ok(responseService.successResponse(RES));
    }
    
}
