package com.api.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.contacts.model.Contact;
import com.api.contacts.service.ContactService;


@RestController
@RequestMapping(value = "contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public ResponseEntity<?> getContacts() {
        return service.getContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable long id) {
        return service.getContactById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Contact contact) {
        return service.createContact(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable long id, @RequestBody Contact updatedContact) {
        return service.updateContact(id, updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable long id) {
        return service.deleteContact(id);
    }
}
