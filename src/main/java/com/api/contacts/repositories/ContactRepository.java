package com.api.contacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.contacts.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}
