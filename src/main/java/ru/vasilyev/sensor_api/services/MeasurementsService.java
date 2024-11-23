package ru.vasilyev.sensor_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasilyev.sensor_api.models.Measurement;
import ru.vasilyev.sensor_api.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private MeasurementsRepository measurementsRepository;
    private SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository,
                               SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;

    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Measurement findById(int id) {
        return measurementsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()));
        measurement.setRecordedAt(LocalDateTime.now());
    }

}
