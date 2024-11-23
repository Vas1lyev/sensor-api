package ru.vasilyev.sensor_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasilyev.sensor_api.models.Sensor;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
