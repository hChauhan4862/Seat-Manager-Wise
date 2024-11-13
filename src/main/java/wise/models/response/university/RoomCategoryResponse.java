package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import wise.models.enums.RoomCategoryType;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategoryResponse {
    private Integer roomCatCode;
    private RoomCategoryType roomCatName;
}
