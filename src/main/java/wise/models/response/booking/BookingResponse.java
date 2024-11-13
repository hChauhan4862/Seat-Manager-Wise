package wise.models.response.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import wise.models.enums.BookingType;
import wise.models.enums.PlatformType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private String bookingId; // DATE + TIME(ns) + SEAT_CODE
    private String userId;
    private String userSchoolNo;
    private String userName;
    private PlatformType reservationMode; // K: Kiosk, W: Web, M: Mobile
    private String reservationDtime;
    private String reservationFrom;
    private String reservationTill;
    private int reservationStatusCode;
    private int floorCode;
    private int roomCode;
    private int zoneCode;
    private Integer managerAction;
    private String managerBookingId;
    private String extensionDtime;
    private Integer extensionCount;
    private boolean changeSeatStatus;
    private String seatChangeDttime;
    private BookingType bookingTypeCode; // GENERAL, CAREL, GROUP, etc.
    private Integer reserveDeskNo;
    private Integer reserveDeskCode;
}
