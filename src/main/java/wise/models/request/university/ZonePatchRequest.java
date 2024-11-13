package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ZonePatchRequest {

  //   @Schema(example = "Main Zone")
    private String zoneName;

   //  @Schema(example = "map/path/to/zone.png")
    private String zoneMap;

   //  @Schema(example = "35.6895,139.6917")
    private String zoneCoords;

  //   @Schema(example = "true")
    private Boolean active;
}
