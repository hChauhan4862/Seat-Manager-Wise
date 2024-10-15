package wise.dto.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SysConfigDto {

    @NotBlank(message = "{sysproperties.key.required}")
    @JsonProperty("key")
    private String key;

    @NotBlank(message = "{sysproperties.value.required}")
    @JsonProperty("value")
    private String value;

    // Getters and Setters
}
