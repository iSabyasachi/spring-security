package com.spring.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{

}
