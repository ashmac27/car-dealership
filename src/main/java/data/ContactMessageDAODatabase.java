package data;

import java.util.List;
import model.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class ContactMessageDAODatabase implements ContactMessageDAO {
    
    private final JdbcTemplate template;

    @Autowired
    public ContactMessageDAODatabase(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public ContactMessage add(ContactMessage contactMessage) {
        final String sql = "INSERT INTO contact_message () VALUES ()";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContactMessage> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContactMessage getContactMessageById(int contactMessageId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ContactMessage contactMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteByContactMessageId(int contactMessageId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
