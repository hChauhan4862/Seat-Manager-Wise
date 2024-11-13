package wise.models.response.university;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {
    private Integer libCode;
    private String libName;
    private String libLocation;
    private Integer univCode;
    private Boolean active;
}
