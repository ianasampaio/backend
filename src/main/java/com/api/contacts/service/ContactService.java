package com.api.contacts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.contacts.model.Contact;
import com.api.contacts.repositories.ContactRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public ResponseEntity<?> getContacts() {
        List<Contact> contacts = repository.findAll();

        if (!contacts.isEmpty()) {
            return ResponseEntity.ok(contacts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum contato encontrado.");
        }
    }

    public ResponseEntity<?> getContactById(@PathVariable long id) {
        Optional<Contact> contact = repository.findById(id);

        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato com o ID " + id + " não foi encontrado.");
        }
    }

    public ResponseEntity<?> createContact(@RequestBody Contact contact) {
        try {
            Contact result = repository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o contato: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateContact(@PathVariable long id, @RequestBody Contact updatedContact) {
        try {
            Optional<Contact> existingContact = repository.findById(id);

            if (existingContact.isPresent()) {
                Contact contact = existingContact.get();
                contact.setName(updatedContact.getName());
                contact.setEmail(updatedContact.getEmail());
                contact.setPhone(updatedContact.getPhone());

                Contact result = repository.save(contact);

                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato com o ID " + id + " não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o contato: " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteContact(@PathVariable long id) {
        try {
            Optional<Contact> contact = repository.findById(id);

            if (contact.isPresent()) {
                repository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato com o ID " + id + " não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o contato: " + e.getMessage());
        }
    }
}
