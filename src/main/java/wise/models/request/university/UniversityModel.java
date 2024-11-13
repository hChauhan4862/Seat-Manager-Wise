package wise.models.request.university;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UniversityModel {

    @NotBlank
    @Size(max = 100)
    private String univName;

    @Size(max = 255)
    private String univAddress;

    @Size(max = 50)
    private String univCity;

    @Size(max = 50)
    private String univState;

    @Size(max = 10)
    private String univZip;

    @Size(max = 15)
    private String univPhone;

    @Size(max = 15)
    private String univPhoneAlt;

    @Size(max = 255)
    private String univWebsite;

    private Float univLatitude;
    private Float univLongitude;
}
