package com.fireDetection.cot.repositories;
import jakarta.nosql.mapping.Repository;
import com.fireDetection.cot.entities.sensorSmoke;

import java.util.stream.Stream;

public interface SmokesensorRepository extends Repository<sensorSmoke, String> {
    Stream<sensorSmoke> findAll();
}