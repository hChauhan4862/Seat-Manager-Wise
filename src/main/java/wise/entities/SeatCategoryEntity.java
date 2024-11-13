package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.SeatCategory;

@Data
@Entity
@Table(name = "WN_SEAT_CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class SeatCategoryEntity extends BaseEntity {

    @Id
    @Column(name = "SEATCAT_CODE", nullable = false)
    private int seatCatCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEATCAT_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private SeatCategory seatCatName;
}
