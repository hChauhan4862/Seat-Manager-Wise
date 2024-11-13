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
@Table(name = "WN_SCHEDULES")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class ScheduleEntity extends BaseEntity {

    @Id
    @Column(name = "SCHEDULE_CODE", nullable = false)
    private Integer scheduleCode;

    @Column(name = "SCHEDULE_TYPE_CODE", nullable = false)
    private Integer scheduleTypeCode;

    @Column(name = "START_DATE", columnDefinition = "nvarchar(8)")
    private String startDate;

    @Column(name = "END_DATE", columnDefinition = "nvarchar(8)")
    private String endDate;
}
