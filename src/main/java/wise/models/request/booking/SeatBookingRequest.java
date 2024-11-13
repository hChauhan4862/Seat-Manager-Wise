package wise.models.request.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class SeatBookingRequest {

    @Schema(example = "2023-11-05T12:00:00")
    private String reservationFrom;

    @Schema(example = "2023-11-05T14:00:00")
    private String reservationTill;

    @NotNull
    @Schema(example = "1")
    private Integer reserveDeskCode;    
}
