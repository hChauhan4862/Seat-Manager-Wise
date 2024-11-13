package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.UserCodeType;

@Data
@Entity
@Table(name = "WN_ROOM_USERCODES_MAP")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class RoomUserCodesMapEntity extends BaseEntity {

    @Id
    @Column(name = "SEQ", nullable = false)
    private int seq;

    @Column(name = "ROOM_CODE", nullable = false)
    private int roomCode;

    @Column(name = "USER_CODE", nullable = false)
    private int userCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_CODE_TYPE", nullable = false, columnDefinition = "varchar(15)")
    private UserCodeType userCodeType; // DEPARTMENT, POSITION, STATUS

    @Column(name = "SCHEDULE_TYPE_CODE", nullable = false)
    private int scheduleTypeCode;

    @Column(name = "ACTIVE", columnDefinition = "bit default 1", nullable = false)
    private boolean active;
}
