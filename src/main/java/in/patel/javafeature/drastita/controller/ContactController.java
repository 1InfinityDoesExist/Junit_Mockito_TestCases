package in.patel.javafeature.drastita.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import in.patel.javafeature.drastita.entity.Contact;
import in.patel.javafeature.drastita.model.requst.ContactCreateRequest;
import in.patel.javafeature.drastita.model.response.ContactCreateResponse;
import io.swagger.annotations.ApiOperation;

@Component
public interface ContactController {

  @ApiOperation(value = "/", consumes = "application/json", produces = "application/json",
      response = Contact.class, notes = "Persiste Contact Object In The Database")
  @RequestMapping(path = "/", method = RequestMethod.POST, consumes = "application/json",
      produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public ResponseEntity<?> createContact(ContactCreateRequest contactCreateRequest);

  @ApiOperation(value = "/{id}", produces = "application/json",
      response = ContactCreateResponse.class)
  @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResponseEntity<?> getContactById(Long id);

  @ApiOperation(value = "/}", produces = "application/json", response = Contact.class,
      responseContainer = "LIST")
  @RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResponseEntity<?> getAllContact();

  @ApiOperation(value = "/{id}", produces = "application/json", response = Contact.class)
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResponseEntity<?> deleteContactById(Long id);

  @ApiOperation(value = "/", consumes = "application/json", produces = "application/json",
      response = Contact.class)
  @RequestMapping(path = "/", method = RequestMethod.PUT, consumes = "application/json",
      produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResponseEntity<?> updateContactById(ContactCreateRequest contactCreateRequest, Long id);


  @ApiOperation(value = "/keys", consumes = "application/json", produces = "application/json")
  @RequestMapping(value = "/keys", method = RequestMethod.POST, consumes = "application/json",
      produces = "application/json")
  public ResponseEntity<?> keys(Map jsonNode);


  @ApiOperation(value = "/keyValue", consumes = "application/json", produces = "application/json")
  @RequestMapping(value = "/keyValue", method = RequestMethod.POST, consumes = "application/json",
      produces = "application/json")
  public ResponseEntity<?> keyValue(Map json);
}
