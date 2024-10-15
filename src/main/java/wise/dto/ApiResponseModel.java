package wise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Generic API Response Model")
public class ApiResponseModel {

    @Schema(description = "Indicates if the operation was successful")
    private boolean success;

    @Schema(description = "Response code", example = "200")
    private int code;

    @Schema(description = "Response data", nullable = true)
    private Object data;

    @Schema(description = "A message describing the result", example = "Request was successful")
    private String msg;

}
