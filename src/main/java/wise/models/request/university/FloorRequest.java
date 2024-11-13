package wise.models.request.university;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class FloorRequest {

    @NotNull
   //  @Schema(example = "1")
    private Integer floorNumber;

    @NotBlank
   //  @Schema(example = "First Floor")
    private String floorName;

    // @Schema(example = "map/path/to/floor.png")
    private String floorMap;

  //   @Schema(example = "blue")
    private String floorColor;

    @NotNull
 //    @Schema(example = "101")
    private Integer libCode;

    @NotNull
 //    @Schema(example = "true")
    private Boolean active;
}
