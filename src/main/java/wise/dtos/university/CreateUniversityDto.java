package wise.dtos.university;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUniversityDto {

    @NotBlank(message = "{university.name.required}")
    @JsonProperty("univ_name")
    @Size(max = 100)
    private String univName;

    @NotBlank(message = "{university.address.required}")
    @JsonProperty("univ_address")
    private String univAddress;

    @NotBlank(message = "{university.city.required}")
    @JsonProperty("univ_city")
    private String univCity;

    @NotBlank(message = "{university.state.required}")
    @JsonProperty("univ_state")
    private String univState;

    @NotBlank(message = "{university.zip.required}")
    @JsonProperty("univ_zip")
    private String univZip;

    @NotBlank(message = "{university.phone.required}")
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
