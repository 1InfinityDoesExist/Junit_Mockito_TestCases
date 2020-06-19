package in.patel.javafeature.drastita.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import in.patel.javafeature.drastita.entity.Contact;
import in.patel.javafeature.drastita.exception.ApplicationException;
import in.patel.javafeature.drastita.exception.Error;
import in.patel.javafeature.drastita.model.requst.ContactCreateRequest;
import in.patel.javafeature.drastita.repository.ContactRepository;
import in.patel.javafeature.drastita.service.ContactService;
import lombok.extern.slf4j.Slf4j;

@Service
@lombok.Data
@Slf4j
public class ContactServiceImpl implements ContactService {

  @Autowired
  private ContactRepository contactRepository;

  @Override
  public Contact createContact(ContactCreateRequest contactCreateRequest) {
    // TODO Auto-generated method stub
    log.info(":::::::::::::ContactServiceImpl Class, createContact method():::::::::::::");
    Contact contact = contactRepository.findContactByEmail(contactCreateRequest.getEmail());
    if (contact != null) {
      throw new ApplicationException(HttpStatus.CONFLICT, Error.EMAIL_ALREADY_EXIST,
          "Email " + contact.getEmail() + " Already Exist");
    }
    contact = new Contact();
    contact.setEmail(contactCreateRequest.getEmail());
    contact.setFirstName(contactCreateRequest.getFirstName());
    contact.setLastName(contactCreateRequest.getLastName());
    contact.setPhoneNumber(contactCreateRequest.getPhoneNumber());
    contact.setComplexJson(contactCreateRequest.getComplexJson());
    contactRepository.save(contact);
    log.info(":::::::::::::::::Contact Id {}", contact.getId());
    return contact;
  }

  @Override
  public Contact getContactById(Long id) {
    // TODO Auto-generated method stub
    log.info(":::::::::::::::::::ContactServiceImpl Class, getContactById()::::::::::::");
    Contact contact = contactRepository.findContactByIdAndDeleteFlag(id, false);
    if (contact == null) {
      throw new ApplicationException(HttpStatus.NOT_FOUND, Error.CONTACT_DOES_NOT_EXIST,
          "No Contact with ID: " + id + " Exist");
    }
    return contact;
  }

  @Override
  public Map<Integer, List<Contact>> getAllContact() {
    // TODO Auto-generated method stub
    log.info("::::::::::::::::ContactService Class, getAllContact::::::::::::::::::");
    List<Contact> listOfContacts = contactRepository.findContactByDeleteFlag(false);
    Map<Integer, List<Contact>> partionContact = partition(listOfContacts, 5);
    return partionContact;
  }

  public static Map<Integer, List<Contact>> partition(List<Contact> list, int pageSize) {
    return IntStream.iterate(0, i -> i + pageSize).limit((list.size() + pageSize - 1) / pageSize)
        .boxed().collect(Collectors.toMap(i -> i / pageSize,
            i -> list.subList(i, Math.min(i + pageSize, list.size()))));
  }

  @Override
  public void deleteContactById(Long id) {
    // TODO Auto-generated method stub
    log.info(":::::::::::ContactService Class, deleteContactById::::::::::::::::::::");
    Contact contact = contactRepository.findContactByIdAndDeleteFlag(id, false);
    if (contact == null) {
      throw new ApplicationException(HttpStatus.NOT_FOUND, Error.CONTACT_DOES_NOT_EXIST,
          "No Contact with ID: " + id + " Exist");
    }
    contactRepository.updateContactByIdAndDeleteFlag(id);
    return;
  }

  @Override
  public Contact updateContactById(ContactCreateRequest contactCreateRequest, Long id) {
    // TODO Auto-generated method stub
    log.info(":::::::::::::ContactService Class, updateContactById method::::::::::::::::::");
    Contact contactFromDB = contactRepository.findContactByIdAndDeleteFlag(id, false);
    if (contactFromDB == null) {
      throw new ApplicationException(HttpStatus.NOT_FOUND, Error.CONTACT_DOES_NOT_EXIST,
          "No Contact with ID: " + id + " Exist");
    }
    contactFromDB.setFirstName(
        (contactCreateRequest.getFirstName() != null ? contactCreateRequest.getFirstName() : null));
    contactFromDB.setLastName(
        contactCreateRequest.getLastName() != null ? contactCreateRequest.getLastName() : null);
    contactFromDB.setPhoneNumber(
        contactCreateRequest.getPhoneNumber() != null ? contactCreateRequest.getPhoneNumber()
            : null);
    contactRepository.save(contactFromDB);
    return contactFromDB;
  }

  @Override
  public List<String> findKeys(String parent, Map<String, Object> treeMap, List<String> keys) {
    // TODO Auto-generated method stub
    log.info("::::::::::::::::findKeys method, ContactServiceImpl Class::::::::");
    treeMap.forEach((key, value) -> {
      if (value instanceof Map) {
        log.info("::::::::::::Inside First If Case::::::::::::");
        Map<String, Object> map = (Map<String, Object>) value;
        findKeys(parent + key + ".", map, keys);
        log.info("::::::::::::::::Inside First If Case, after recursive call:::::::::::::");
      }
      if (value instanceof List) {
        log.info(":::::::::::::Inside second If Condition:::::::::::::::::");
        List map = (List) value;
        if (map.size() > 0) {
          if (((List) value).get(0) instanceof Map) {
            findKeys(parent + key + "[].", (Map<String, Object>) map.get(0), keys);
          }
        }
      }
      if (parent.isEmpty()) {
        log.info("::::::::::::::::::::Inside 3rd If Condition, keys.add(key)::::::");
        keys.add(key);

      } else {
        log.info("::::::::::::::::::::Inside 3rd Else Condition, keys.add(key)::::::");
        keys.add(parent + key);

      }
    });

    log.info("::::::::::::::::::::Before Return Statemant::::::::::::::::::::::");
    return keys;
  }

  @Override
  public ModelMap findKeysValue(String parent, Map<String, Object> treeMap, List<String> keys,
      ModelMap response) {
    // TODO Auto-generated method stub
    treeMap.forEach((key, value) -> {
      if (value instanceof Map) {
        Map<String, Object> map = (Map<String, Object>) value;
        findKeysValue(parent + key + ".", map, keys, response);
      }
      if (value instanceof List) {
        List map = (List) value;
        if (map.size() > 0) {
          if (map.get(0) instanceof Map) {
            findKeysValue(parent + key + "[].", (Map<String, Object>) map.get(0), keys, response);
          }
        }
      }
      if (parent.isEmpty()) {
        keys.add(key);
      } else {
        keys.add(parent + key);
      }
    });
    return response;
  }


}
