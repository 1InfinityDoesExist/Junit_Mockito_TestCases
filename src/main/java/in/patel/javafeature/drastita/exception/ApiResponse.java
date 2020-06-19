package in.patel.javafeature.drastita.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiResponse {

  private String error;
  private String errorDescription;

}
