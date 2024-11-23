package ru.vasilyev.sensor_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasilyev.sensor_api.models.Sensor;
import ru.vasilyev.sensor_api.repositories.SensorsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Sensor findById(int id) {
        return sensorsRepository.findById(id).orElse(null);
    }

    public Sensor findByName(String name) {
        return sensorsRepository.findByName(name).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
