package com.example.Day14Workshop.service;

import com.example.Day14Workshop.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service 
public class ContactsRedis implements ContactsRepo{
        private final String CONTACT_CACHE = "CONTACT";

        @Autowired
        RedisTemplate<String, Contact> redisTemplate;

        @Override
        public void findById(final String contactId){
                redisTemplate.opsForValue().get(contactId);
        }
    
}
