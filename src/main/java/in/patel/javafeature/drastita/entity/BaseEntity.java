package in.patel.javafeature.drastita.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import in.patel.javafeature.drastita.model.ContactView;
import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
@lombok.Data
public class BaseEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ContactView.class)
  @Column(name = "id")
  @ApiModelProperty(notes = "Its A Unique Key Assigned TO Each Object", value = "Primary Key",
      name = "id", dataType = "Long", example = "02941")
  private Long id;

  @Column(name = "delete_flag")
  @ApiModelProperty(notes = "Set The DeleteFlag to True if Its Deleted, OrElse False",
      value = "SofDelete", name = "deleteFlag", dataType = "Boolean", example = "True")
  private boolean deleteFlag;

  @JsonView(ContactView.class)
  @CreationTimestamp
  @Column(name = "creation_date")
  @ApiModelProperty(notes = "CreationDate of the Object", value = "Date Of Creation",
      name = "creationDate", dataType = "LocalDateTime", example = "2014-10-10'T'10:10:10.555'Z'")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime creationDate;

  @JsonView(ContactView.class)
  @UpdateTimestamp
  @Column(name = "modification_date")
  @ApiModelProperty(notes = "Time when the object was Modified", value = "Date Of Modification",
      name = "modificationDate", example = "2014-10-10'T'10:10:10.555'Z'")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime modificationDate;

}
