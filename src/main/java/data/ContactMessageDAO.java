package data;

import java.util.List;
import model.ContactMessage;

public interface ContactMessageDAO {
    
    public ContactMessage add(ContactMessage contactMessage);
    
    public List<ContactMessage> getAll();
    
    public ContactMessage getContactMessageById(int contactMessageId);
    
    public boolean update(ContactMessage contactMessage);
    
    public boolean deleteByContactMessageId(int contactMessageId);
}