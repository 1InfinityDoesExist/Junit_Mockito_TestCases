package in.patel.javafeature.drastita.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

@lombok.Data
@JsonInclude(Include.NON_NULL)
public class ContactCreateResponse implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private Long id;
  private LocalDateTime creationDate;
  private LocalDateTime modificationDate;
  private JsonNode complexJson;

}
