package in.patel.javafeature.drastita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setError(ex.getError().getMessage());
    apiResponse.setErrorDescription(ex.getDebugMessage());
    return buildResponseEntity(apiResponse, ex.getHttpStatus());
  }

  public ResponseEntity<?> buildResponseEntity(ApiResponse apiResponse, HttpStatus httpStatus) {
    return new ResponseEntity<>(apiResponse, httpStatus);
  }
}
