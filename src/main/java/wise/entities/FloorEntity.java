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
@Table(name = "WN_FLOORS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class FloorEntity extends BaseEntity {

    @Id
    @Column(name = "FLOOR_CODE", nullable = false)
    private Integer floorCode;

    @Column(name = "FLOOR_NUMBER", nullable = false)
    private Integer floorNumber;

    @Column(name = "FLOOR_NAME", columnDefinition = "nvarchar(50)")
    private String floorName;

    @Column(name = "FLOOR_MAP", columnDefinition = "nvarchar(255)")
    private String floorMap;

    @Column(name = "FLOOR_COLOR", columnDefinition = "nvarchar(255)")
    private String floorColor;

    @Column(name = "LIB_CODE", nullable = false)
    private Integer libCode;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "bit default 1")
    private Boolean active;
}
