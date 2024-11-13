package wise.models.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import wise.models.enums.SeatCategory;

@Data
public class SeatCategoryRequest {
    @NotNull
   //  @Schema(example = "REGULAR")
    private SeatCategory seatCatName;
}
