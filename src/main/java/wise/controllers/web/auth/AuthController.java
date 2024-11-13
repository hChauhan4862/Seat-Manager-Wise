package wise.controllers.web.auth;

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
@RequestMapping(Constant.WEB_BASE_API + "auth")
@Tag(name = "Auth Manager", description = "Authentication Management")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("/login")
    private final ResponseEntity<ApiResponseModel> sigininController(@Valid @RequestBody SigninRequest signinRequest) {
        String response = authService.Signin(signinRequest);
        return ResponseEntity.ok(responseService.successResponse("authentication.loginSuccessfully", response));
    }
}