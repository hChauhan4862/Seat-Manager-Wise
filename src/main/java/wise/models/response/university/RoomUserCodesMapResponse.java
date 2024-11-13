package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import wise.models.enums.UserCodeType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUserCodesMapResponse {
    private int seq;
    private int roomCode;
    private int userCode;
    private UserCodeType userCodeType; // DEPARTMENT, POSITION, STATUS
    private int scheduleTypeCode;
    private boolean active;
}
