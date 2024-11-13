package wise.models.request.user_management;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PositionsModel {

    @Size(min = 0, max = 10)
    private String posCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String posName;

    // Getters and Setters
}
