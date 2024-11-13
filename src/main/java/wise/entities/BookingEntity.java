package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.BookingType;
import wise.models.enums.PlatformType;

@Data
@Entity
@Table(name = "WN_BOOKING")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class BookingEntity extends BaseEntity{

    @Id
    @Column(name = "BOOKING_ID", columnDefinition = "varchar(30)")
    private String bookingId; // DATE + TIME(ns) + SEAT_CODE

    // User Information
    @Column(name = "USER_ID", nullable = false, columnDefinition = "varchar(30)")
    private String userId;

    @Column(name = "USER_SCHOOL_NO", columnDefinition = "varchar(30)")
    private String userSchoolNo;

    @Column(name = "USER_NAME", nullable = false, columnDefinition = "nvarchar(100)")
    private String userName;

    // Reservation Information
    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_MODE", columnDefinition = "char(1)")
    private PlatformType reservationMode; // K: Kiosk, W: Web, M: Mobile

    @Column(name = "RESERVATION_DTIME", columnDefinition = "varchar(23)")
    private String reservationDtime;

    @Column(name = "RESERVATION_FROM", columnDefinition = "varchar(23)")
    private String reservationFrom;

    @Column(name = "RESERVATION_TILL", columnDefinition = "varchar(23)")
    private String reservationTill;

    @Column(name = "RESERVATION_STATUS_CODE", nullable = false)
    private int reservationStatusCode;

    // Seat Information
    @Column(name = "FLOOR_CODE", nullable = false)
    private int floorCode;

    @Column(name = "ROOM_CODE", nullable = false)
    private int roomCode;

    @Column(name = "ZONE_CODE", nullable = false)
    private int zoneCode;

    // Manager Information
    @Column(name = "MANAGER_ACTION")
    private Integer managerAction;

    @Column(name = "MANAGER_BOOKING_ID", columnDefinition = "varchar(30)")
    private String managerBookingId;

    // Extension Information
    @Column(name = "EXTENSION_DTTIME", columnDefinition = "varchar(23)")
    private String extensionDtime;

    @Column(name = "EXTENSION_COUNT")
    private Integer extensionCount;

    @Column(name = "CHANGE_SEAT_STATUS", nullable = false, columnDefinition = "bit default 1")
    private boolean changeSeatStatus;

    @Column(name = "SEAT_CHANGE_DTTIME", columnDefinition = "varchar(23)")
    private String seatChangeDttime;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOKING_TYPE_CODE", columnDefinition = "varchar(10)")
    private BookingType bookingTypeCode; // GENERAL, CAREL, GROUP, etc.

    @Column(name = "RESERVE_DESK_NO")
    private Integer reserveDeskNo;

    @Column(name = "RESERVE_DESK_CODE")
    private Integer reserveDeskCode;
}
