package wise.models.response.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingUsersInfoResponse {

    private String bookingId; // DATE + TIME(ms) + SEAT_CODE
    private String userId;
    private String userName;
    private String schoolNo;
    private String reservationStatusCode;
    private String confirmationDtime;
    private String returnDtime;
}
