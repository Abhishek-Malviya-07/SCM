package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;
// use this for repositary to interact database
@Repository
public interface UserRepo extends JpaRepository<User, String> {

    //extra methods to add db operations
    //custom query methods

    Optional<User> findByEmail(String email);
}
