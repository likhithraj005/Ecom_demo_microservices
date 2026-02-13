package com.likhith.microservices.usermservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likhith.microservices.usermservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
