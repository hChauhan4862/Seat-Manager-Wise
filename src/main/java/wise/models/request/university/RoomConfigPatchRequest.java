package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import wise.models.enums.RoomConfigState;

@Data
public class RoomConfigPatchRequest {

   //  @Schema(example = "101")
    private Integer roomCode;

  //   @Schema(example = "08:00")
    private String commonOpenTime;

 //    @Schema(example = "17:00")
    private String commonCloseTime;

   //  @Schema(example = "60")
    private Integer fixedUseTime;

//     @Schema(example = "true")
    private Boolean extensionAllowed;

  //   @Schema(example = "true")
    private Boolean useUseTimeAsExtension;

  //   @Schema(example = "30")
    private Integer extensionTime;

 //    @Schema(example = "5")
    private Integer extensionLimit;

 //    @Schema(example = "true")
    private Boolean allowFixedSeat;

 //    @Schema(example = "1,2,3")
    private String fixedSeatsNumbers;

 //    @Schema(example = "NOT_IN_USE")
    private RoomConfigState positionState;

   //  @Schema(example = "NOT_IN_USE")
    private RoomConfigState departmentState;

  //   @Schema(example = "NOT_IN_USE")
    private RoomConfigState statusState;

 //    @Schema(example = "1")
    private Integer zoneCode;

  //   @Schema(example = "1")
    private Integer scheduleTypeCode;
}
