package wise.models.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import wise.models.enums.UserCodeType;

@Data
public class RoomUserCodesMapRequest {

    @NotNull
  //   @Schema(example = "1")
    private int roomCode;

    @NotNull
  //   @Schema(example = "1001")
    private int userCode;

    @NotNull
  //   @Schema(example = "DEPARTMENT")
    private UserCodeType userCodeType; // DEPARTMENT, POSITION, STATUS

    @NotNull
//     @Schema(example = "1")
    private int scheduleTypeCode;

    @NotNull
   //  @Schema(example = "true")
    private boolean active;
}
