package com.sg.cardealership.model;

import java.util.Objects;

public class ContactMessage {
    int contactMessageId;
    String name, message, phone, email;

    public ContactMessage(int contactMessageId, String name, String message, String phone, String email) {
        this.contactMessageId = contactMessageId;
        this.name = name;
        this.message = message;
        this.phone = phone;
        this.email = email;
    }

    public ContactMessage() {
    }

    public int getContactMessageId() {
        return contactMessageId;
    }

    public void setContactMessageId(int contactMessageId) {
        this.contactMessageId = contactMessageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactMessage message1 = (ContactMessage) o;
        return contactMessageId == message1.contactMessageId && Objects.equals(name, message1.name) && Objects.equals(message, message1.message) && Objects.equals(phone, message1.phone) && Objects.equals(email, message1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactMessageId, name, message, phone, email);
    }
}
