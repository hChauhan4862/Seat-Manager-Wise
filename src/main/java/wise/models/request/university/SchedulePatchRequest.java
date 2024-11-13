package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class SchedulePatchRequest {

    @Schema(example = "1")
    private Integer scheduleCode;

    @Schema(example = "1")
    private Integer scheduleTypeCode;

    @Schema(example = "20240101")
    private String startDate; // in 'yyyyMMdd' format

    @Schema(example = "20241231")
    private String endDate; // in 'yyyyMMdd' format

}
