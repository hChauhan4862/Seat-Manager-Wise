package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Integer roomCode;
    private String roomNumber;
    private String roomName;
    private String roomCoords;
    private Integer roomCapacity;
    private String roomMap;
    private Integer roomSeatsStart;
    private Integer roomSeatsEnd;
    private Integer roomCatCode;
    private Integer floorCode;
    private Boolean active;
}
