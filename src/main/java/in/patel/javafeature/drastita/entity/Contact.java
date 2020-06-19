package in.patel.javafeature.drastita.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import in.patel.javafeature.drastita.model.ContactView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Contact")
@Table(name = "Contact", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@ApiModel(value = "Contact", description = "Contact Details Of a Particular User")
@lombok.Data
public class Contact extends BaseEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @JsonView(ContactView.class)
  @Column(name = "first_name")
  @ApiModelProperty(notes = "First Name Of The Person", value = "First Name", name = "firstName",
      dataType = "String", example = "Rocky")
  private String firstName;

  @JsonView(ContactView.class)
  @Column(name = "last_name")
  @ApiModelProperty(notes = "Last Name Of the Person", value = "Last Name", name = "lastName",
      dataType = "String", example = "Bhai")
  private String lastName;


  @JsonView(ContactView.class)
  @Column(name = "email", unique = true)
  @ApiModelProperty(notes = "Email Id Of The Person", value = "Person's Email ID", name = "email",
      dataType = "String", example = "xyz@gmail.com")

  private String email;

  @JsonView(ContactView.class)
  @Column(name = "phone_number")
  @ApiModelProperty(notes = "Phone Number Of The Person", value = "Person's PhoneNumber",
      name = "phoneNumber", dataType = "String", example = "9354125136")
  private String phoneNumber;

  @JsonView(ContactView.class)
  @Column(name = "complex_json", columnDefinition = "text", length = 10485760)
  @ApiModelProperty(notes = "Complex Json", value = "Complex Json", name = "complexJson",
      dataType = "String", example = "")
  private String complexJson;

}
