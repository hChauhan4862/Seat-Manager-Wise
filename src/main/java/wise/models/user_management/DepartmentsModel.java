package wise.models.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DepartmentsModel {

    @NotBlank(message = "{departments.name.required}")
    @Size(min = 3, max = 50, message = "{departments.name.size}")
    @JsonProperty("dept_name")
    private String deptName;

    // Getters and Setters
}
