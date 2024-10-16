package wise.dtos.university;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUniversityDto {

    @JsonProperty("univ_name")
    @Size(max = 100)
    private String univName;

    @JsonProperty("univ_address")
    private String univAddress;

    @JsonProperty("univ_city")
    private String univCity;

    @JsonProperty("univ_state")
    private String univState;

    @JsonProperty("univ_zip")
    private String univZip;

    @JsonProperty("univ_phone")
    private String univPhone;

    @JsonProperty("univ_phone_alt")
    private String univPhoneAlt;

    @JsonProperty("univ_website")
    private String univWebsite;

    @JsonProperty("univ_latitude")
    private Float univLatitude;

    @JsonProperty("univ_longitude")
    private Float univLongitude;

    // Getters and Setters
}
