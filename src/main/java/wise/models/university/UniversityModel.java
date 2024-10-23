package wise.models.university;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UniversityModel {

    @NotBlank(message = "{university.name.required}")
    @Size(max = 100, message = "{university.name.size}")
    private String univName;

    @Size(max = 255, message = "{university.address.size}")
    private String univAddress;

    @Size(max = 50, message = "{university.city.size}")
    private String univCity;

    @Size(max = 50, message = "{university.state.size}")
    private String univState;

    @Size(max = 10, message = "{university.zip.size}")
    private String univZip;

    @Size(max = 15, message = "{university.phone.size}")
    private String univPhone;

    @Size(max = 15, message = "{university.phoneAlt.size}")
    private String univPhoneAlt;

    @Size(max = 255, message = "{university.website.size}")
    private String univWebsite;

    private Float univLatitude;
    private Float univLongitude;
}
