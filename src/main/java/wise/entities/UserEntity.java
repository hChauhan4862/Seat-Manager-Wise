package wise.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "wn_usr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SoftDelete
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // use Nvarchar(10) for user_id
    @Column(name = "user_id", nullable = false , unique = true, columnDefinition = "Nvarchar(10)")
    private String user_id;

    @Column(name = "user_name", nullable = false, columnDefinition = "Nvarchar(50)")
    private String user_name;

    @Column(name = "barcode", nullable = false, columnDefinition = "Nvarchar(50)")
    private String barcode;

    @Column(name = "school_no", nullable = false, columnDefinition = "Nvarchar(50)")
    private String school_no;

    @Column(name = "position_code", nullable = false, columnDefinition = "Nvarchar(10)")
    private String position_code;

    @Column(name = "position_name", nullable = false, columnDefinition = "Nvarchar(50)")
    private String position_name;

    @Column(name = "dept_code", nullable = false, columnDefinition = "Nvarchar(10)")
    private String dept_code;

    @Column(name = "dept_name", nullable = false, columnDefinition = "Nvarchar(50)")
    private String dept_name;

    @Column(name = "status_code", nullable = false, columnDefinition = "Nvarchar(10)")
    private String status_code;

    @Column(name = "status_name", nullable = false, columnDefinition = "Nvarchar(50)")
    private String status_name;

    @Column(name = "phone", nullable = true, columnDefinition = "Nvarchar(20)")
    private String phone;

    @Column(name = "mobile", nullable = true, columnDefinition = "Nvarchar(20)")
    private String mobile;

    @Column(name = "address", nullable = true, columnDefinition = "Nvarchar(100)")
    private String address;

    @Column(name = "usr_password", nullable = false, columnDefinition = "Nvarchar(255)")
    private String usr_password;

    @Column(name = "usr_password_change_date", nullable = false, columnDefinition = "Nvarchar(14)")
    private String usr_password_change_date;

    @Column(name = "auth_token", nullable = true, columnDefinition = "Nvarchar(255)")
    private String auth_token;

   @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}