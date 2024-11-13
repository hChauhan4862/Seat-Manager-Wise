package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.BookingActionBy;
import wise.models.enums.BookingActionCode;

@Data
@Entity
@Table(name = "WN_BOOKING_USER_HISTORY")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class BookingUserHistoryEntity extends BaseEntity {
    @Id
    @Column(name = "BOOKING_ID", columnDefinition = "varchar(30)")
    private String bookingId; // DATE + TIME(ms) + SEAT_CODE

    @Column(name = "USER_ID", columnDefinition = "varchar(30)")
    private String userId;

    @Column(name = "USER_NAME", columnDefinition = "nvarchar(100)")
    private String userName;

    @Column(name = "SCHOOL_NO", columnDefinition = "varchar(30)")
    private String schoolNo;

    @Column(name = "RESERVATION_STATUS_CODE", columnDefinition = "varchar(10)")
    private String reservationStatusCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_CODE", columnDefinition = "varchar(30)")
    private BookingActionCode actionCode; // BOOKED, CONFIRMED, EXTENDED, CANCELLED, RETURNED, AUTO_CANCELLED, AUTO_RETURNED

    @Column(name = "ACTION_DTTIME", columnDefinition = "varchar(23)")
    private String actionDtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_BY", columnDefinition = "varchar(30)")
    private BookingActionBy actionBy; // PC/SELF/MANAGER
}
