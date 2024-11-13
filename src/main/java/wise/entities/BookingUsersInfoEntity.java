package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "WN_BOOKING_USERS_INFO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class BookingUsersInfoEntity extends BaseEntity {

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

    @Column(name = "CONFIRMATION_DTTIME", columnDefinition = "varchar(23)")
    private String confirmationDtime;

    @Column(name = "RETURN_DTTIME", columnDefinition = "varchar(23)")
    private String returnDtime;
}
