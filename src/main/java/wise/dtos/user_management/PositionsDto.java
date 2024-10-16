package wise.dtos.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PositionsDto {

    @NotBlank(message = "{positions.name.required}")
    @Size(min = 3, max = 50, message = "{positions.name.size}")
    @JsonProperty("pos_name")
    private String posName;

    // Getters and Setters
}
