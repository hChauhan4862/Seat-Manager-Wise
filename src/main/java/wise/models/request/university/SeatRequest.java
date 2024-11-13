package wise.models.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class SeatRequest {

    @NotNull
  //   @Schema(example = "101")
    private int seatCode;

    @NotNull
  //   @Schema(example = "1")
    private int seatNumber;

  //   @Schema(example = "Aisle Seat")
    private String seatName;

    @NotNull
  //   @Schema(example = "1001")
    private int roomCode;

    @NotNull
  //   @Schema(example = "1")
    private int zoneCode;

   //  @Schema(example = "10.1234,20.5678")
    private String seatCoords;

    @NotNull
 //    @Schema(example = "1")
    private String seatCatCode;
}
