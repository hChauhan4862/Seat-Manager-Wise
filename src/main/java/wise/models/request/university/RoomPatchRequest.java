package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class RoomPatchRequest {

    @Schema(example = "101")
    private String roomNumber;

    @Schema(example = "Lecture Hall 1")
    private String roomName;

    @Schema(example = "35.6895,139.6917")
    private String roomCoords;

    @Schema(example = "30")
    private Integer roomCapacity;

    @Schema(example = "map/path/to/room.png")
    private String roomMap;

    @Schema(example = "1")
    private Integer roomSeatsStart;

    @Schema(example = "10")
    private Integer roomSeatsEnd;

    @Schema(example = "1002")
    private Integer roomCatCode;

    @Schema(example = "101")
    private Integer floorCode;

    @Schema(example = "true")
    private Boolean active;
}
