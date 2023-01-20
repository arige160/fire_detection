package com.fireDetection.cot.repositories;

import com.fireDetection.cot.entities.user;
import jakarta.nosql.mapping.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends Repository<user, String> {
    Optional<user> findByEmail(String email);
    Stream<user> findAll();
}