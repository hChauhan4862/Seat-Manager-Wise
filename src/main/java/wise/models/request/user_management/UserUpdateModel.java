package wise.models.request.user_management;

// import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateModel {

    @Max(1000000)
    private Integer userSeq;

    @NotBlank
    @Size(min = 0, max = 30)
    private String userId;

    @NotBlank
    @Size(min = 0, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 0, max = 50)
    private String userSchoolNo;

    @Size(min = 0, max = 50)
    private String userBarcode;

    @Size(min = 0, max = 10)
    private String userDeptCode;

    @Size(min = 0, max = 50)
    private String userDeptName;

    @Size(min = 0, max = 10)
    private String userPosCode;

    @Size(min = 0, max = 50)
    private String userPosName;

    @Size(min = 0, max = 10)
    private String userStatusCode;

    @Size(min = 0, max = 50)
    private String userStatusName;

    @Size(min = 0, max = 20)
    private String userMobile;

    @Size(min = 0, max = 20)
    private String userPhone;

    @Size(min = 0, max = 255)
    private String userAddress;

    @NotBlank
    @Size(min = 0, max = 255)
    private String userPassword;

    // Getters and Setters
}
