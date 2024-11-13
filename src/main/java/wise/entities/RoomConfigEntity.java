package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;
import wise.models.enums.RoomConfigState;


@Entity
@Table(name = "WN_ROOM_CONFIG")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class RoomConfigEntity extends BaseEntity{
    
   @EmbeddedId
   private RoomConfigId roomConfigId;
    
    @Default
    @Column(name = "ROOM_COMMON_OPEN_TIME", columnDefinition = "nvarchar(8)")
    private String commonOpenTime = "09:00:00";

    @Default
    @Column(name = "ROOM_COMMON_CLOSE_TIME", columnDefinition = "nvarchar(8)")
    private String commonCloseTime = "18:00:00";

    @Default
    @Column(name = "ROOM_FIXED_USETIME")
    private int fixedUseTime = 120;

    @Default
    @Column(name = "ROOM_EXTENSION_ALLOWED", nullable = false, columnDefinition = "bit default 1")
    private boolean extensionAllowed = true;

    @Default
    @Column(name = "ROOM_USE_USETIME_AS_EXTENSION", nullable = false, columnDefinition = "bit default 1")
    private boolean roomUseTimeAsExtension = true;

    @Default
    @Column(name = "ROOM_EXTESION_TIME")
    private int extensionTime = 60;

    @Default
    @Column(name = "ROOM_EXTENSION_LIMIT")
    private int extensionLimit = 1;

    @Default
    @Column(name = "ROOM_ALLOW_FIXED_SEAT", nullable = false, columnDefinition = "bit default 1")
    private boolean allowFixedSeat = true;

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

    @Default
    @Column(name = "ROOM_NEED_CONFIRMATION", nullable = false,  columnDefinition = "bit default 0")
    private Boolean roomNeedConfirmation = false;

    @Default
    @Column(name = "ROOM_CONFIRMATION_WAITING_TIME")
    private Integer roomConfirmationWaitingTime = 10;

    @Default
    @Column(name = "ROOM_NEED_APPROVAL", nullable = false,  columnDefinition = "bit default 0")
    private Boolean roomNeedApproval = false;

    @Default
    @Column(name = "ROOM_APPROVAL_WAITING_TIME")
    private Integer roomApprovalWaitingTime = 10;

    @Default
    @Column(name = "ROOM_AUTO_CANCELLATION", nullable = false,  columnDefinition = "bit default 0")
    private Boolean roomAutoCancellation = true;

    @Default
    @Column(name = "ROOM_AUTO_CANCELLATION_TIME")
    private Integer roomAutoCancellationTime = 10;

    @PrePersist
    private void applyDefaultStates() {
        if (positionState == null) positionState = RoomConfigState.NOT_IN_USE;
        if (departmentState == null) departmentState = RoomConfigState.NOT_IN_USE;
        if (statusState == null) statusState = RoomConfigState.NOT_IN_USE;
    }
    
}
