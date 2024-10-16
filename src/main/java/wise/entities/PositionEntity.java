package wise.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "WN_USER_POSITIONS")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
public class PositionEntity extends BaseEntity {

    @Id
    @Column(name = "USER_POS_CODE", nullable = false, columnDefinition = "Nvarchar(10)")
    private String posCode;

    @Column(name = "USER_POS_NAME", nullable = false, unique = true, columnDefinition = "Nvarchar(50)")
    private String posName;
}
