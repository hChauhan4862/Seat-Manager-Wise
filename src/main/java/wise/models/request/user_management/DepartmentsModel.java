package wise.models.request.user_management;

import io.swagger.v3.oas.annotations.media.Schema;

// import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DepartmentsModel {

    @Size(min = 0, max = 10)
    // @Schema(example = "DPT123", description = "The code of the department")
    private String deptCode;

    @NotBlank
    @Size(min = 3, max = 50)
    // @Schema(example = "Human Resources", description = "The name of the department")
    private String deptName;
}
