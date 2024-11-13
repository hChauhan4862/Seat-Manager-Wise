package wise.models.request.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import wise.models.enums.BookingStatus;

@Data
public class BookingStatusRequest {

    @NotNull
    @Schema(example = "BOOKED")
    private BookingStatus reservationStatusName; // BOOKED, CONFIRMED, IN_USE, CANCELLED, RETURNED
}
