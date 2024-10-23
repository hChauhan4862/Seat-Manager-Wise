package wise.entities;

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
    @Column(name = "UNIV_CODE", nullable = false)
    private int univCode;

    @Column(name = "UNIV_NAME", unique = true, nullable = false, length = 100)
    private String univName;

    @Column(name = "UNIV_ADDRESS", length = 255)
    private String univAddress;

    @Column(name = "UNIV_CITY", length = 50)
    private String univCity;

    @Column(name = "UNIV_STATE", length = 50)
    private String univState;

    @Column(name = "UNIV_ZIP", length = 10)
    private String univZip;

    @Column(name = "UNIV_PHONE", length = 15)
    private String univPhone;

    @Column(name = "UNIV_PHONE_ALT", length = 15)
    private String univPhoneAlt;

    @Column(name = "UNIV_WEBSITE", length = 255)
    private String univWebsite;

    @Column(name = "UNIV_LATITUDE")
    private Float univLatitude;

    @Column(name = "UNIV_LONGITUDE")
    private Float univLongitude;
}
