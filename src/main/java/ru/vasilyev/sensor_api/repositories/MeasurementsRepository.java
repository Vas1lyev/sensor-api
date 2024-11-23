package ru.vasilyev.sensor_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasilyev.sensor_api.models.Measurement;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
