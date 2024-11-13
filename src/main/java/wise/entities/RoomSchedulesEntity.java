package wise.entities;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "WN_ROOM_SCHEDULES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoomSchedulesEntity {

    @EmbeddedId
    private RoomSchedulesId roomSchedulesId;

    @Default
    @Column(name = "IS_APPLIED", nullable = false)
    private Boolean isApplied = false;

    @Default
    @Column(name = "IS_OPERATIONAL", nullable = false)
    private Boolean isOperational = false;

    @Default
    @Column(name = "ROOM_OPEN_TIME", nullable = false, columnDefinition = "nvarchar(8)")
    private String roomOpenTime = "09:00:00";

    @Default
    @Column(name = "ROOM_CLOSE_TIME", nullable = false, columnDefinition = "nvarchar(8)")
    private String roomCloseTime = "18:00:00";
}
