package in.patel.javafeature.drastita.exception;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private final Error error;
  private final String debugMessage;
  private final HttpStatus httpStatus;

  public ApplicationException(HttpStatus httpStatus, Error error, String debugMessage) {
    super(error.getMessage(), null);
    this.error = error;
    this.debugMessage = debugMessage;
    this.httpStatus = httpStatus;
  }



}
