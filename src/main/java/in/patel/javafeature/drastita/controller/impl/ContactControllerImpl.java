package in.patel.javafeature.drastita.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import in.patel.javafeature.drastita.controller.ContactController;
import in.patel.javafeature.drastita.entity.Contact;
import in.patel.javafeature.drastita.model.ContactCreate;
import in.patel.javafeature.drastita.model.ContactUpdate;
import in.patel.javafeature.drastita.model.ContactView;
import in.patel.javafeature.drastita.model.requst.ContactCreateRequest;
import in.patel.javafeature.drastita.model.response.ContactCreateResponse;
import in.patel.javafeature.drastita.service.ContactService;
import in.patel.javafeature.drastita.util.StringToJsonConversion;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/contact")
@Slf4j
public class ContactControllerImpl implements ContactController {


  @Autowired
  private ContactService contactService;

  @Override
  public ResponseEntity<?> createContact(@Validated(ContactCreate.class) @RequestBody(
      required = true) ContactCreateRequest contactCreateRequest) {
    // TODO Auto-generated method stub
    Contact contact = contactService.createContact(contactCreateRequest);
    ContactCreateResponse contactCreateResponse = new ContactCreateResponse();
    contactCreateResponse.setId(contact.getId());
    return new ResponseEntity<ContactCreateResponse>(contactCreateResponse, HttpStatus.CREATED);
  }

  // @JsonView(ContactView.class)
  @Override
  public ResponseEntity<?> getContactById(@ApiParam(value = "id", required = true, name = "id",
      type = "Long", example = "0291") @PathVariable(value = "id", required = true) Long id) {
    // TODO Auto-generated method stub
    log.info(":::::::::::::::ContactControllerImpl Class, getContactById method:::::::::::");
    Contact contact = contactService.getContactById(id);
    log.info("::::::::::::::contact {}", contact);
    ContactCreateResponse contactCreateResponse = new ContactCreateResponse();
    contactCreateResponse.setEmail(contact.getEmail());
    contactCreateResponse.setFirstName(contact.getFirstName());
    contactCreateResponse.setLastName(contact.getLastName());
    contactCreateResponse.setPhoneNumber(contact.getPhoneNumber());
    contactCreateResponse
        .setComplexJson(StringToJsonConversion.stringToJson(contact.getComplexJson()));
    return new ResponseEntity<ContactCreateResponse>(contactCreateResponse, HttpStatus.OK);
  }

  @JsonView(ContactView.class)
  @Override
  public ResponseEntity<?> getAllContact() {
    // TODO Auto-generated method stub
    log.info("::::::::::ContactControllerImpl Class, getAllContact:::::::::::::::::");
    Map<Integer, List<Contact>> listOfContacts = contactService.getAllContact();
    return new ResponseEntity<Map<Integer, List<Contact>>>(listOfContacts, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> deleteContactById(@ApiParam(value = "id", required = true, name = "id",
      type = "Long", example = "0291") @PathVariable(value = "id", required = true) Long id) {
    // TODO Auto-generated method stub
    log.info("::::ContactControllerImpl Class, deleteContactById method:::::::::::");
    contactService.deleteContactById(id);
    return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> updateContactById(
      @Validated(ContactUpdate.class) @RequestBody(
          required = true) ContactCreateRequest contactCreateRequest,
      @ApiParam(value = "id", required = true, name = "id", type = "Long",
          example = "0291") @RequestParam(value = "id", required = true) Long id) {
    // TODO Auto-generated method stub
    log.info("::::::::ContactControllerImpl Class, updateContactById method ()::::::::::");
    Contact contactResponse = contactService.updateContactById(contactCreateRequest, id);
    log.info("::::::contactResponse {}", contactResponse);
    return new ResponseEntity<Contact>(contactResponse, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> keys(@RequestBody Map json) {
    // TODO Auto-generated method stub
    ModelMap modelMap = new ModelMap();
    modelMap.put("keys",
        contactService.findKeys("", (Map<String, Object>) json.get("data"), new ArrayList<>()));
    return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> keyValue(@RequestBody Map json) {
    // TODO Auto-generated method stub
    ModelMap map = contactService.findKeysValue("", (Map<String, Object>) json.get("data"),
        new ArrayList<>(), new ModelMap());
    return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
  }

}
