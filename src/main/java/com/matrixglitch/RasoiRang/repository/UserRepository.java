package com.matrixglitch.RasoiRang.repository;

import com.matrixglitch.RasoiRang.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String email);
    Optional<User> findByEmail(String email);

}

