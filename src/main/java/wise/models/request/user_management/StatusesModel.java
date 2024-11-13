package wise.models.request.user_management;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StatusesModel {

    @Size(min = 0, max = 10)
    private String statusCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String statusName;
}
