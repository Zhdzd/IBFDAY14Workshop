package com.example.Day14Workshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Day14Workshop.model.Contact;
import com.example.Day14Workshop.service.ContactRedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class AddressbookController {
    private static final Logger logger = LoggerFactory.getLogger(AddressbookController.class);
    
    @Autowired
    ContactRedis service;
    private ApplicationArguments applicationArguments;

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new com.example.Day14Workshop.model.Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
        logger.info("contactId > " + contactId);
        Contact ctc = service.findById(contactId); //ws14
        logger.info("getId > " + ctc.getId());
        logger.info("getEmail > " + ctc.getEmail()); 
        return "showContact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        logger.info("Name > " + contact.getName());
        logger.info("Email > " + contact.getEmail());
        logger.info("Phone Number > " + contact.getPhoneNumber());
        Contact persistToRedisCtc = new Contact( //ws14
            contact.getName(),
            contact.getEmail(),
            contact.getPhoneNumber()
        );
        service.save(persistToRedisCtc); //added for ws14
        return "showContact";
    }
}
