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
@Table(name = "WN_ZONES")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class ZoneEntity extends BaseEntity {

    @Id
    @Column(name = "ZONE_CODE", nullable = false)
    private int zoneCode;

    @Column(name = "ZONE_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private String zoneName;

    @Column(name = "ZONE_MAP", columnDefinition = "varchar(255)")
    private String zoneMap;

    @Column(name = "ZONE_COORDS", columnDefinition = "varchar(25)")
    private String zoneCoords;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "bit default 1")
    private boolean active;
}
