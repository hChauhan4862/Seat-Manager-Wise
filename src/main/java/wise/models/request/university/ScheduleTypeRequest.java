package wise.models.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ScheduleTypeRequest {

    @NotNull
  //   @Schema(example = "WEEKLY")
    private String scheduleTypeName;
}
