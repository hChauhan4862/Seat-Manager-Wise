package wise.models.request.booking;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class BookingUsersInfoRequest {

    @NotBlank
    @Schema(example = "20231105123045_123")
    private String bookingId; // DATE + TIME(ms) + SEAT_CODE

    @NotBlank
    @Schema(example = "user123")
    private String userId;

    @NotBlank
    @Schema(example = "John Doe")
    private String userName;

    @Schema(example = "school123")
    private String schoolNo;

    @Schema(example = "CONFIRMED")
    private String reservationStatusCode;

    @Schema(example = "2023-11-05T12:30:00")
    private String confirmationDtime;

    @Schema(example = "2023-11-05T14:30:00")
    private String returnDtime;
}
