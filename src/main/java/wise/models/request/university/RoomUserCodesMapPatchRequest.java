package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import wise.models.enums.UserCodeType;

@Data
public class RoomUserCodesMapPatchRequest {

   //  @Schema(example = "1")
    private Integer roomCode;

  //   @Schema(example = "1001")
    private Integer userCode;

  //   @Schema(example = "DEPARTMENT")
    private UserCodeType userCodeType; // DEPARTMENT, POSITION, STATUS

  //   @Schema(example = "1")
    private Integer scheduleTypeCode;

   //  @Schema(example = "true")
    private Boolean active;
}
