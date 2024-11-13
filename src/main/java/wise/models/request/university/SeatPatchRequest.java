package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class SeatPatchRequest {

  //   @Schema(example = "101")
    private Integer seatCode;

 //    @Schema(example = "1")
    private Integer seatNumber;

  //   @Schema(example = "Aisle Seat")
    private String seatName;

  //   @Schema(example = "1001")
    private Integer roomCode;

 //    @Schema(example = "1")
    private Integer zoneCode;

   //  @Schema(example = "10.1234,20.5678")
    private String seatCoords;

 //    @Schema(example = "1")
    private String seatCatCode;
}
