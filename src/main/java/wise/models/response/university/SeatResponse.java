package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private int seatCode;
    private int seatNumber;
    private String seatName;
    private int roomCode;
    private int zoneCode;
    private String seatCoords;
    private String seatCatCode;
}
