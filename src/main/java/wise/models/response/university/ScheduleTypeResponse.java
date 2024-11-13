package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTypeResponse {
    private int scheduleTypeCode;
    private String scheduleTypeName;
}
