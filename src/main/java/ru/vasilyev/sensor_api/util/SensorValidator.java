package ru.vasilyev.sensor_api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasilyev.sensor_api.dto.SensorDTO;
import ru.vasilyev.sensor_api.services.SensorsService;

@Component
public class SensorValidator implements Validator {
    private SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorsService.findByName(sensorDTO.getName()) != null) {
            errors.rejectValue("name", "", "This name is already taken");
        }

    }
}
