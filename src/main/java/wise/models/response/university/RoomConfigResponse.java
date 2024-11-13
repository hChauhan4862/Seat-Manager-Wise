package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import wise.models.enums.RoomConfigState;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomConfigResponse {
    private int roomCode;
    private String commonOpenTime;
    private String commonCloseTime;
    private int fixedUseTime;
    private boolean extensionAllowed;
    private boolean useUseTimeAsExtension;
    private int extensionTime;
    private int extensionLimit;
    private boolean allowFixedSeat;
    private String fixedSeatsNumbers;
    private RoomConfigState positionState;
    private RoomConfigState departmentState;
    private RoomConfigState statusState;
    private int zoneCode;
    private int scheduleTypeCode;
}
