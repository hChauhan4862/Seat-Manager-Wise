package wise.models.request.booking;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import wise.models.enums.BookingType;
import wise.models.enums.PlatformType;

@Data
public class BookingPatchRequest {

    // @Schema(example = "20231105123045_123")
    private String bookingId; // DATE + TIME(ns) + SEAT_CODE

    // @Schema(example = "user123")
    private String userId;

    // @Schema(example = "school123")
    private String userSchoolNo;

    // @Schema(example = "John Doe")
    private String userName;

    // @Schema(example = "K")
    private PlatformType reservationMode; // K: Kiosk, W: Web, M: Mobile

    // @Schema(example = "2023-11-05T12:30:00")
    private String reservationDtime;

    // @Schema(example = "2023-11-05T12:00:00")
    private String reservationFrom;

    // @Schema(example = "2023-11-05T14:00:00")
    private String reservationTill;

    // @Schema(example = "1")
    private Integer reservationStatusCode;

    // @Schema(example = "1")
    private Integer floorCode;

    // @Schema(example = "1")
    private Integer roomCode;

    // @Schema(example = "1")
    private Integer zoneCode;

    // @Schema(example = "1")
    private Integer managerAction;

    // @Schema(example = "manager_booking_123")
    private String managerBookingId;

    // @Schema(example = "2023-11-05T12:30:00")
    private String extensionDtime;

    // @Schema(example = "0")
    private Integer extensionCount;

    // @Schema(example = "true")
    private Boolean changeSeatStatus;

    // @Schema(example = "2023-11-05T12:30:00")
    private String seatChangeDttime;

    // @Schema(example = "GENERAL")
    private BookingType bookingTypeCode; // GENERAL, CAREL, GROUP, etc.

    // @Schema(example = "1")
    private Integer reserveDeskNo;

    // @Schema(example = "desk_123")
    private Integer reserveDeskCode;
}
