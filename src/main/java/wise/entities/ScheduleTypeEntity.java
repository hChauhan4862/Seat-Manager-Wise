package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.RoomConfigState;

@Data
@Entity
@Table(name = "WN_SCHEDULE_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class ScheduleTypeEntity extends BaseEntity {

    @Id
    @Column(name = "SCHEDULE_TYPE_CODE", nullable = false)
    private int scheduleTypeCode;

    @Column(name = "SCHEDULE_TYPE_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private String scheduleTypeName;

    @Default
    @Column(name = "ROOM_COMMON_OPEN_TIME", columnDefinition = "nvarchar(8)")
    private String commonOpenTime = "09:00:00";

    @Default
    @Column(name = "ROOM_COMMON_CLOSE_TIME", columnDefinition = "nvarchar(8)")
    private String commonCloseTime = "18:00:00";

    @Default
    @Column(name = "ROOM_FIXED_USETIME")
    private Integer fixedUseTime = 120;

    @Default
    @Column(name = "ROOM_EXTENSION_ALLOWED", nullable = false, columnDefinition = "bit default 1")
    private boolean extensionAllowed = true;

    @Default
    @Column(name = "ROOM_USE_USETIME_AS_EXTENSION", nullable = false, columnDefinition = "bit default 1")
    private boolean roomUseTimeAsExtension = true;

    @Default
    @Column(name = "ROOM_EXTESION_TIME")
    private Integer extensionTime = 60;

    @Default
    @Column(name = "ROOM_EXTENSION_LIMIT")
    private Integer extensionLimit = 1;

    @Column(name = "ROOM_ALLOW_FIXED_SEAT", nullable = false, columnDefinition = "bit default 1")
    private boolean allowFixedSeat;

    @Column(name = "ROOM_FIXED_SEATS_NUMBERS", columnDefinition = "varchar(255)")
    private String fixedSeatsNumbers;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_POSITION_STATE", nullable = false, columnDefinition = "nvarchar(50) default 'NOT_IN_USE'")
    private RoomConfigState positionState;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_DEPARTMENT_STATE", nullable = false, columnDefinition = "nvarchar(50) default 'NOT_IN_USE'")
    private RoomConfigState departmentState;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_STATUS_STATE", nullable = false, columnDefinition = "nvarchar(50) default 'NOT_IN_USE'")
    private RoomConfigState statusState;

    @Column(name = "ROOM_NEED_CONFIRMATION", nullable = false, columnDefinition = "bit default 0")
    private Boolean roomNeedConfirmation;

    @Default
    @Column(name = "ROOM_CONFIRMATION_WAITING_TIME")
    private Integer roomConfirmationWaitingTime = 10;

    @Column(name = "ROOM_NEED_APPROVAL", nullable = false, columnDefinition = "bit default 0")
    private Boolean roomNeedApproval;

    @Default
    @Column(name = "ROOM_APPROVAL_WAITING_TIME")
    private Integer roomApprovalWaitingTime = 10;

    @Column(name = "ROOM_AUTO_CANCELLATION", nullable = false, columnDefinition = "bit default 0")
    private Boolean roomAutoCancellation;

    @Default
    @Column(name = "ROOM_AUTO_CANCELLATION_TIME")
    private Integer roomAutoCancellationTime = 10;
}
