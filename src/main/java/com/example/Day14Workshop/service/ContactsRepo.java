package com.example.Day14Workshop.service;

import com.example.Day14Workshop.model.Contact;

public interface ContactsRepo {
    public void save(final Contact ctc);
    public void findById(final String contactId);
}
