package wise.controllers.kiosk;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import wise.common.ApiResponseModel;
import wise.models.request.auth.SigninRequestKiosk;
import wise.services.app.ResponseService;
import wise.services.auth.AuthService;
import wise.utils.constant.Constant;

@RestController
@RequiredArgsConstructor
@Tag(name = "Kiosk", description = "Mobile-specific APIs")
@RequestMapping(Constant.KIOSK_BASE_API)
public class KioskRoutes {
    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("auth/login")
    private final ResponseEntity<ApiResponseModel> sigininController(
            @Valid @RequestBody SigninRequestKiosk signinRequest) {
        String response = authService.SigninKiosk(signinRequest);
        return ResponseEntity.ok(responseService.successResponse("authentication.loginSuccessfully", response));
    }
}
