package com.sg.cardealership.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import com.sg.cardealership.model.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

@Repository
public class ContactMessageDAODatabase implements ContactMessageDAO {
    
    private final JdbcTemplate template;

    @Autowired
    public ContactMessageDAODatabase(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public ContactMessage add(ContactMessage contactMessage) {
        final String sql = "INSERT INTO contact_message (Name, Message, Email, Phone) VALUES (?, ?, ?, ?)";
        template.update(sql,contactMessage.getName(), contactMessage.getMessage(), contactMessage.getEmail(), contactMessage.getPhone());
        int newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contactMessage.setContactMessageId(newId);
        return contactMessage;
    }

    @Override
    public List<ContactMessage> getAll() {
        final String sql = "SELECT * FROM contact_message";
        return template.query(sql, new ContactMessageMapper());
    }

    @Override
    public ContactMessage getContactMessageById(int contactMessageId) {
        final String sql = "SELECT * FROM contact_message";
        return template.queryForObject(sql, new ContactMessageMapper(), contactMessageId);
    }

    @Override
    public boolean update(ContactMessage contactMessage) {
        final String sql = "UPDATE contact_message SET " +
                "Name = ?, "+
                "Message = ?, "+
                "Email = ?, "+
                "Phone = ? "+
                "WHERE ContactMessageId = ?";
        return template.update(sql, contactMessage.getName(), contactMessage.getMessage(),
                (contactMessage.getEmail()==null) ? new SqlParameterValue(Types.NULL,"Email") : contactMessage.getEmail(),
                (contactMessage.getPhone()==null) ? new SqlParameterValue(Types.NULL,"Phone") : contactMessage.getPhone(),
                contactMessage.getContactMessageId()
            ) > 0;
    }

    @Override
    public boolean deleteByContactMessageId(int contactMessageId) {
        final String sql = "DELETE FROM contact_message WHERE ContactMessageId = ?";
        return template.update(sql, contactMessageId) > 0;
    }
    
    private final class ContactMessageMapper implements RowMapper<ContactMessage> {

        @Override
        public ContactMessage mapRow(ResultSet rs, int i) throws SQLException {
            ContactMessage result = new ContactMessage();
            result.setContactMessageId(rs.getInt("ContactMessageId"));
            result.setName(rs.getString("Name"));
            result.setMessage(rs.getString("Message"));
            try {
                result.setEmail(rs.getString("Email"));
            } catch(Exception e) { } // Email is null
            try {
                result.setPhone(rs.getString("Phone"));
            } catch(Exception e) { } // Phone is null
            return result;
        }
    }
}
