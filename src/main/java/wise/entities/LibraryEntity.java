package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "WN_LIBRARY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class LibraryEntity extends BaseEntity {

    @Id
    @Column(name = "LIB_CODE", nullable = false)
    private Integer libCode;

    @Column(name = "LIB_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private String libName;

    @Column(name = "LIB_LOCATION", columnDefinition = "nvarchar(255)")
    private String libLocation;

    @Column(name = "UNIV_CODE", nullable = false)
    private Integer univCode;

    @Column(name = "ACTIVE", columnDefinition = "bit default 1")
    private Boolean active;
}
