package ru.vasilyev.sensor_api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasilyev.sensor_api.dto.MeasurementDTO;
import ru.vasilyev.sensor_api.models.Sensor;
import ru.vasilyev.sensor_api.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {
    private SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        Sensor existingSensor = sensorsService.findByName(measurementDTO.getSensor().getName());
        if (existingSensor == null) {
            System.out.println("Sensor with this name not found");
            errors.rejectValue("sensor", "", "Sensor with this name not found");
        }
    }
}