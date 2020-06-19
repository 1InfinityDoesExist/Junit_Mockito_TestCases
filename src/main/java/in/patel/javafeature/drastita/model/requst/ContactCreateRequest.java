package in.patel.javafeature.drastita.model.requst;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import in.patel.javafeature.drastita.model.ContactCreate;
import in.patel.javafeature.drastita.model.ContactUpdate;

@lombok.Data
public class ContactCreateRequest implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  @NotNull(groups = {ContactCreate.class},
      message = "Email Attribute Must Not Be Null In Case Of Create")
  @Null(groups = {ContactUpdate.class}, message = "Email Attribute Must Be Null In Case Of Update")
  private String email;
  private String phoneNumber;
  @NotNull(groups = {ContactCreate.class},
      message = "ComplexJson Attribute Must Not Be Null In Case Of Update")
  private String complexJson;

}
