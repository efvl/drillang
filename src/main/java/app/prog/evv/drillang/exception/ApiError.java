package app.prog.evv.drillang.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@Builder
@Schema(description = "The object of error information")
public class ApiError {

    @Schema(required = true, example = "2022-11-09T12:42:16.757195300Z", description = "The time when error appears")
    private Instant timestamp;

    @Schema(required = true, example = "404", description = "Error code")
    private HttpStatus status;

    @Schema(required = true, example = "Language was not found by id = 5", description = "Error description")
    private String message;

    @Schema(required = false, description = "Stack trace of error")
    private String trace;

}
