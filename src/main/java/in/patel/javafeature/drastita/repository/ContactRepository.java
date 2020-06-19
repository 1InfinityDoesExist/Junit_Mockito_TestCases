package in.patel.javafeature.drastita.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import in.patel.javafeature.drastita.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  public Contact findContactByEmail(String email);

  public List<Contact> findContactByDeleteFlag(boolean b);

  // @Query("Select Contact from #{#entityName} Contact where id = ?1 and deleteFlag = ?2")
  public Contact findContactByIdAndDeleteFlag(Long id, boolean b);


  @Transactional
  @Modifying
  @Query("Update #{#entityName} set deleteFlag=true where id=?1")
  public void updateContactByIdAndDeleteFlag(Long id);

}
