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
@Table(name = "WN_ROOM")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class RoomEntity extends BaseEntity {
    @Id
    @Column(name = "ROOM_CODE", nullable = false)
    private Integer roomCode;

    @Column(name = "ROOM_NUMBER", columnDefinition = "varchar(10)", nullable = false)
    private String roomNumber;

    @Column(name = "ROOM_NAME", columnDefinition = "nvarchar(50)", nullable = false)
    private String roomName;

    @Column(name = "ROOM_COORDS", columnDefinition = "varchar(25)")
    private String roomCoords;

    @Column(name = "ROOM_CAPACITY")
    private Integer roomCapacity;

    @Column(name = "ROOM_MAP", columnDefinition = "nvarchar(255)")
    private String roomMap;

    @Column(name = "ROOM_SEATS_START")
    private Integer roomSeatsStart;

    @Column(name = "ROOM_SEATS_END")
    private Integer roomSeatsEnd;

    @Column(name = "ROOMCAT_CODE", nullable = false)
    private Integer roomCatCode;

    @Column(name = "FLOOR_CODE", nullable = false)
    private Integer floorCode;
    
    @Column(name = "ZONE_CODE", nullable = false)
    private int zoneCode;

    @Column(name = "ACTIVE", columnDefinition = "bit default 1", nullable = false)
    private Boolean active;
}
