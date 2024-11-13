package wise.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "WN_USERS")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Id
    @Column(name = "USER_SEQ", nullable = false, columnDefinition = "bigint")
    private Integer userSeq;

    @Column(name = "USER_ID", nullable = false, columnDefinition = "varchar(30)")
    private String userId;

    @Column(name = "USER_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    private String userName;

    @Column(name = "USER_SCHOOL_NO", nullable = false, columnDefinition = "varchar(50)")
    private String userSchoolNo;

    @Column(name = "USER_BARCODE", columnDefinition = "varchar(50)")
    private String userBarcode;

    @Column(name = "USER_DEPT_CODE", columnDefinition = "nvarchar(10)")
    private String userDeptCode;

    @Column(name = "USER_DEPT_NAME", columnDefinition = "nvarchar(50)")
    private String userDeptName;

    @Column(name = "USER_POS_CODE", columnDefinition = "nvarchar(10)")
    private String userPosCode;

    @Column(name = "USER_POS_NAME", columnDefinition = "nvarchar(50)")
    private String userPosName;

    @Column(name = "USER_STATUS_CODE", columnDefinition = "nvarchar(10)")
    private String userStatusCode;

    @Column(name = "USER_STATUS_NAME", columnDefinition = "nvarchar(50)")
    private String userStatusName;

    @Column(name = "USER_MOBILE", columnDefinition = "nvarchar(20)")
    private String userMobile;

    @Column(name = "USER_PHONE", columnDefinition = "nvarchar(20)")
    private String userPhone;

    @Column(name = "USER_ADDRESS", columnDefinition = "nvarchar(255)")
    private String userAddress;

    @Column(name = "USER_PASSWORD", columnDefinition = "nvarchar(255)")
    private String userPassword;

    @Column(name = "USER_PASS_CHANGE_DTTIME", columnDefinition = "varchar(23)")
    private String userPassChangeDttime;

    @Column(name = "AUTH_TOKEN", columnDefinition = "nvarchar(255)")
    private String authToken;
}
