package wise.controllers.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import wise.common.ApiResponseModel;
import wise.models.request.auth.SigninRequest;
import wise.services.app.ResponseService;
import wise.services.auth.AuthService;
import wise.utils.constant.Constant;

@RestController
@Tag(name = "Mobile", description = "Mobile-specific APIs")
@RequestMapping(Constant.MOBILE_BASE_API)
@RequiredArgsConstructor
public class MobileRoutes {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("/auth/login")
    private final ResponseEntity<ApiResponseModel> sigininController(@Valid @RequestBody SigninRequest signinRequest) {
        String response = authService.Signin(signinRequest);
        return ResponseEntity.ok(responseService.successResponse("authentication.loginSuccessfully", response));
    }


}
