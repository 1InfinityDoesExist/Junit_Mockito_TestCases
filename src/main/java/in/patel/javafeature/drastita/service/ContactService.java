package in.patel.javafeature.drastita.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import in.patel.javafeature.drastita.entity.Contact;
import in.patel.javafeature.drastita.model.requst.ContactCreateRequest;

@Component
public interface ContactService {

  public Contact createContact(ContactCreateRequest cotact);

  public Contact getContactById(Long id);

  public Map<Integer, List<Contact>> getAllContact();

  public void deleteContactById(Long id);

  public List<String> findKeys(String parent, Map<String, Object> treeMap, List<String> keys);

  public ModelMap findKeysValue(String parent, Map<String, Object> treeMap, List<String> keys,
      ModelMap response);

  public Contact updateContactById(ContactCreateRequest contactCreateRequest, Long id);
}
