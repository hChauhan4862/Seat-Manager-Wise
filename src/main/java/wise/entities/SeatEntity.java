package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "WN_SEAT")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class SeatEntity extends BaseEntity {

    @Id
    @Column(name = "SEAT_CODE", nullable = false)
    private int seatCode;

    @Column(name = "SEAT_NUMBER", nullable = false)
    private int seatNumber;

    @Column(name = "SEAT_NAME", columnDefinition = "varchar(50)")
    private String seatName;

    @Column(name = "ROOM_CODE", nullable = false)
    private int roomCode;

    @Column(name = "ZONE_CODE", nullable = false)
    private int zoneCode;

    @Column(name = "SEAT_COORDS", columnDefinition = "varchar(25)")
    private String seatCoords;

    @Column(name = "SEATCAT_CODE", nullable = false, columnDefinition = "varchar(10)")
    private String seatCatCode;
}
