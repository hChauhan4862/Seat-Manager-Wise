package wise.models.request.booking;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class BookingUserHistoryPatchRequest {

    @Schema(example = "user123")
    private String userId;

    @Schema(example = "John Doe")
    private String userName;

    @Schema(example = "school123")
    private String schoolNo;

    @Schema(example = "CONFIRMED")
    private String reservationStatusCode;

    @Schema(example = "BOOKED")
    private String actionCode;

    @Schema(example = "2023-11-05T12:30:00")
    private String actionDtime;

    @Schema(example = "MANAGER")
    private String actionBy;
}
