package wise.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WN_USER_DEPARTMENTS")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentEntity extends BaseEntity {

    @Id
    @Column(name = "USER_DEPT_CODE", nullable = false, columnDefinition = "Nvarchar(10)")
    private String deptCode;

    @Column(name = "USER_DEPT_NAME", nullable = false, unique = true, columnDefinition = "Nvarchar(50)")
    private String deptName;
}
