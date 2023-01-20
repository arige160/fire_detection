package com.fireDetection.cot.repositories;
import jakarta.nosql.mapping.Repository;
import com.fireDetection.cot.entities.sensorFlame;

import java.util.stream.Stream;

public interface FlamesensorRepository extends Repository<sensorFlame, String> {
    Stream<sensorFlame> findAll();
}