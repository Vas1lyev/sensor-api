package ru.vasilyev.sensor_api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.vasilyev.sensor_api.dao.MeasurementDAO;
import ru.vasilyev.sensor_api.dto.MeasurementDTO;
import ru.vasilyev.sensor_api.models.Measurement;
import ru.vasilyev.sensor_api.services.MeasurementsService;
import ru.vasilyev.sensor_api.util.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private MeasurementsService measurementsService;
    private ModelMapper modelMapper;
    private MeasurementValidator measurementValidator;
    private MeasurementDAO measurementDAO;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService,
                                  MeasurementValidator measurementValidator,
                                  MeasurementDAO measurementDAO,
                                  ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
        this.measurementDAO = measurementDAO;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MeasurementDTO getMeasurement(@PathVariable int id) {
        Measurement measurement = measurementsService.findById(id);

        if (measurement == null)
            throw new MeasurementNotFoundException();
        return convertToMeasurementDTO(measurement);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotFoundException e) {
        ErrorResponse response = new ErrorResponse("Measurement with this ID wasn't found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(@RequestBody MeasurementDTO measurementDTO,
                                                        BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage() + " " + "\n");
                errorMsg.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementsService.save(convertToMeasurement(measurementDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Sensor with this name wasn't found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementDAO.getRainyDaysCount();
    }

}
