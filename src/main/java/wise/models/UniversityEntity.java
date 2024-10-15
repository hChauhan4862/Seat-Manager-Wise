package wise.models;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WN_UNIVERSITY")
@SoftDelete
@EqualsAndHashCode(callSuper = true)
@Data
public class UniversityEntity extends BaseEntity {

    @Id
    @Column(name = "UNIVERSITY_CODE", nullable = false)
    private Integer universityCode;

    @Column(name = "UNIVERSITY_NAME", nullable = false, unique = true, columnDefinition = "Nvarchar(50)")
    private String universityName;

    @Column(name = "UNIV_ADDRESS", columnDefinition = "Nvarchar(255)")
    private String universityAddress;

    @Column(name = "UNIV_CITY", columnDefinition = "Nvarchar(50)")
    private String universityCity;

    @Column(name = "UNIV_STATE", columnDefinition = "Nvarchar(50)")
    private String universityState;

    @Column(name = "UNIV_ZIP", columnDefinition = "Nvarchar(10)")
    private String universityZip;

    @Column(name = "UNIV_PHONE", columnDefinition = "Nvarchar(15)")
    private String universityPhone;

    @Column(name = "UNIV_PHONE_ALT", columnDefinition = "Nvarchar(15)")
    private String universityPhoneAlt;

    @Column(name = "UNIV_WEBSITE", columnDefinition = "varchar(255)")
    private String universityWebsite;

    @Column(name = "UNIV_LATITUDE")
    private Float universityLatitude;

    @Column(name = "UNIV_LONGITUDE")
    private Float universityLongitude;
}
