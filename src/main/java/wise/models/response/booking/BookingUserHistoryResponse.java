package wise.models.response.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingUserHistoryResponse {

    private String bookingId; // DATE + TIME(ms) + SEAT_CODE
    private String userId;
    private String userName;
    private String schoolNo;
    private String reservationStatusCode;
    private String actionCode; // BOOKED, CONFIRMED, EXTENDED, CANCELLED, RETURNED, AUTO_CANCELLED,
                               // AUTO_RETURNED
    private String actionDtime;
    private String actionBy; // PC/SELF/MANAGER
}
