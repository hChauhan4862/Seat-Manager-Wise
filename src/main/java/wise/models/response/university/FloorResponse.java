package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorResponse {
    private Integer floorCode;
    private Integer floorNumber;
    private String floorName;
    private String floorMap;
    private String floorColor;
    private Integer libCode;
    private Boolean active;
}
