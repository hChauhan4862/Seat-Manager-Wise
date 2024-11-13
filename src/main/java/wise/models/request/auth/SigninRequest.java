package wise.models.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SigninRequest {
    @NotBlank
    // @Schema(example = "wise01")
    private String username;

    @NotBlank
    // @Schema(example = "password123")
    private String password;
}