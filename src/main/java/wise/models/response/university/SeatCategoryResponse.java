package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import wise.models.enums.SeatCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatCategoryResponse {
    private int seatCatCode;
    private SeatCategory seatCatName;
}
