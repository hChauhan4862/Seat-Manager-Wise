package wise.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "WN_ADMIN_USERS")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdminUserEntity extends BaseEntity {

    @Id
    @Column(name = "USER_SEQ", nullable = false, columnDefinition = "bigint")
    private Integer userSeq;

    @Column(name = "USER_ID", nullable = false, columnDefinition = "varchar(30)")
    private String userId;

    @Default
    @Column(name = "ADMIN_TYPE", nullable = false)
    private String adminType = "SUPER_ADMIN";

    @Column(name = "USER_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false, columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "USER_PASSWORD", columnDefinition = "nvarchar(255)")
    private String userPassword;

    @Column(name = "USER_PASS_CHANGE_DTTIME", columnDefinition = "varchar(23)")
    private String userPassChangeDttime;

    @Column(name = "AUTH_TOKEN", columnDefinition = "nvarchar(255)")
    private String authToken;
}
