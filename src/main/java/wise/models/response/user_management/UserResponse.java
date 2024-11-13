package wise.models.response.user_management;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userSeq;
    private String userId;
    private String userName;
    private String userSchoolNo;
    private String userBarcode;
    private String userDeptCode;
    private String userDeptName;
    private String userPosCode;
    private String userPosName;
    private String userStatusCode;
    private String userStatusName;
    private String userMobile;
    private String userPhone;
    private String userAddress;
    private String userPassChangeDttime;
    private String authToken;

}
