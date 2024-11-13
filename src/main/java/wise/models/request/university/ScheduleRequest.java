package wise.models.request.university;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ScheduleRequest {

    @NotNull
    @Schema(example = "1")
    private Integer scheduleCode;

    @NotNull
    @Schema(example = "1")
    private Integer scheduleTypeCode;

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(example = "20240101")
    private String startDate; // in 'yyyyMMdd' format

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(example = "20241231")
    private String endDate; // in 'yyyyMMdd' format

}
