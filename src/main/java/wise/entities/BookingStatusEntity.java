package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.BookingStatus;

@Data
@Entity
@Table(name = "WN_BOOKING_STATUS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class BookingStatusEntity extends BaseEntity {

    @Id
    @Column(name = "RESERVATION_STATUS_CODE", nullable = false)
    private int reservationStatusCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_STATUS_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private BookingStatus reservationStatusName; // BOOKED, CONFIRMED, IN_USE, CANCELLED, RETURNED
}
