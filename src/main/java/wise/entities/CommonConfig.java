package wise.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WN_COMMON_CONFIG")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonConfig extends BaseEntity {

    @Id
    @Column(name = "CONFIG_KEY", nullable = false, columnDefinition = "Nvarchar(50)")
    private String configKey;

    @Column(name = "CONFIG_VALUE", nullable = false, columnDefinition = "Nvarchar(255)")
    private String configValue;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "Bit")
    private boolean active;
}
