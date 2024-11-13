package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class FloorPatchRequest {

    // @Schema(example = "1")
    private Integer floorNumber;

    // @Schema(example = "First Floor")
    private String floorName;

    // @Schema(example = "map/path/to/floor.png")
    private String floorMap;

    // @Schema(example = "blue")
    private String floorColor;

   //  @Schema(example = "101")
    private Integer libCode;

   //  @Schema(example = "true")
    private Boolean active;
}
