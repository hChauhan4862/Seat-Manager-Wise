package wise.models.response.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import wise.models.enums.BookingStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusResponse {
    private int reservationStatusCode;
    private BookingStatus reservationStatusName; // BOOKED, CONFIRMED, IN_USE, CANCELLED, RETURNED
}
