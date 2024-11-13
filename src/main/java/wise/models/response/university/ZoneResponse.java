package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneResponse {
    private int zoneCode;
    private String zoneName;
    private String zoneMap;
    private String zoneCoords;
    private boolean active;
}
