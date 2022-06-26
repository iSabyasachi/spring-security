package com.spring.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.AccountUser;

@Repository
public interface UserRepository extends CrudRepository<AccountUser, Long>{
	
	List<AccountUser> findByEmail(String email);

}
