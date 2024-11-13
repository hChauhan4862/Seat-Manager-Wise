package wise.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RoomSchedulesId implements Serializable {

    @Column(name = "ROOM_CODE")
    private int roomCode;

    @Column(name = "SCHEDULE_TYPE_CODE")
    private int scheduleTypeCode;

    @Column(name = "WEEKDAY")
    private int weekday;
}

