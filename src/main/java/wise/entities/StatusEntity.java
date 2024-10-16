package wise.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "WN_USER_STATUS")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusEntity extends BaseEntity {

    @Id
    @Column(name = "USER_STATUS_CODE", nullable = false, columnDefinition = "Nvarchar(10)")
    private String statusCode;

    @Column(name = "USER_STATUS_NAME", nullable = false, unique = true, columnDefinition = "Nvarchar(50)")
    private String statusName;
}
