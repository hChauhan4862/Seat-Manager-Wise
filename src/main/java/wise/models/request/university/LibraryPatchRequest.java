package wise.models.request.university;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class LibraryPatchRequest {

 //    @Schema(example = "Library Name")
    private String libName;

   //  @Schema(example = "Location of the Library")
    private String libLocation;

   //  @Schema(example = "1001")
    private Integer univCode;

  //   @Schema(example = "true")
    private Boolean active;
}
