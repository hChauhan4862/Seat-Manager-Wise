package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
    
    private Integer scheduleCode;
    private Integer scheduleTypeCode;
    private String startDate;
    private String endDate;

    // Optional fields if necessary
    private Boolean active; // Or other fields you might need
}
