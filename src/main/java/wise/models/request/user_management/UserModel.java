package wise.models.request.user_management;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UserModel {

    @NotBlank
    @Size(min = 0, max = 30)
    // @Schema(example = "wise01")
    private String userId;

    @NotBlank
    @Size(min = 0, max = 50)
    // @Schema(example = "wise01")
    private String userName;

    @NotBlank
    @Size(min = 0, max = 50)
    // @Schema(example = "SCH123456")
    private String userSchoolNo;

    @Size(min = 0, max = 50)
    // @Schema(example = "BARCODE123")
    private String userBarcode;

    @Size(min = 0, max = 10)
    // @Schema(example = "DPT001")
    private String userDeptCode;

    @Size(min = 0, max = 50)
    // @Schema(example = "Computer Science")
    private String userDeptName;

    @Size(min = 0, max = 10)
    // @Schema(example = "POS002")
    private String userPosCode;

    @Size(min = 0, max = 50)
    // @Schema(example = "Professor")
    private String userPosName;

    @Size(min = 0, max = 10)
    // @Schema(example = "ST001")
    private String userStatusCode;

    @Size(min = 0, max = 50)
    // @Schema(example = "Active")
    private String userStatusName;

    @Size(min = 0, max = 20)
    // @Schema(example = "+1234567890")
    private String userMobile;

    @Size(min = 0, max = 20)
    // @Schema(example = "+0987654321")
    private String userPhone;

    @Size(min = 0, max = 255)
    // @Schema(example = "123 Main St, City, Country")
    private String userAddress;

    @NotBlank
    @Size(min = 0, max = 255)
    // @Schema(example = "password123")
    private String userPassword;
}
