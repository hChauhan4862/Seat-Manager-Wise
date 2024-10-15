package wise.dto.departments;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateDepartmentsDto {

    @NotBlank(message = "{departments.name.required}")
    @JsonProperty("dept_name")
    private String deptName;

    // Getters and Setters
}
