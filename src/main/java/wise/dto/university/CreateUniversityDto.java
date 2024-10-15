package wise.dto.university;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUniversityDto {

    @NotBlank(message = "{university.name.required}")
    @JsonProperty("university_name")
    private String universityName;

    @NotBlank(message = "{university.address.required}")
    @JsonProperty("university_address")
    private String universityAddress;

    @NotBlank(message = "{university.city.required}")
    @JsonProperty("university_city")
    private String universityCity;

    @NotBlank(message = "{university.state.required}")
    @JsonProperty("university_state")
    private String universityState;

    @NotBlank(message = "{university.zip.required}")
    @JsonProperty("university_zip")
    private String universityZip;

    @NotBlank(message = "{university.phone.required}")
    @JsonProperty("university_phone")
    private String universityPhone;

    @JsonProperty("university_phone_alt")
    private String universityPhoneAlt;

    @JsonProperty("university_website")
    private String universityWebsite;

    @JsonProperty("university_latitude")
    private Float universityLatitude;

    @JsonProperty("university_longitude")
    private Float universityLongitude;

    // Getters and Setters
}
