package wise.models.request.university;

import lombok.Data;
import wise.models.enums.RoomCategoryType;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class RoomCategoryRequest {

   //  @Schema(example = "Conference Room")
    private RoomCategoryType roomCatName;
}
