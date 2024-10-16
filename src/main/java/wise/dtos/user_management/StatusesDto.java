package wise.dtos.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StatusesDto {

    @NotBlank(message = "{statuses.name.required}")
    @Size(min = 3, max = 50, message = "{statuses.name.size}")
    @JsonProperty("status_name")
    private String statusName;

    // Getters and Setters
}
