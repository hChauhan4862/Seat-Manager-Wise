package wise.models.request.university;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class LibraryRequest {

    @NotBlank
   //  @Schema(example = "Library Name")
    private String libName;

  //   @Schema(example = "Location of the Library", required = false)
    private String libLocation;

    @NotNull
  //   @Schema(example = "1001")
    private Integer univCode;

    // @Schema(example = "1", required = false)
    // private Boolean active;
}
