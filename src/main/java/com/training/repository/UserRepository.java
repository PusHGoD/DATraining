package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	User findOneByUsername(String username);

}
