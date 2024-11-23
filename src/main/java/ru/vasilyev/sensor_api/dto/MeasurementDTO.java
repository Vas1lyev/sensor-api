package ru.vasilyev.sensor_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

    @Min(-100)
    @Max(100)
    @NotNull
    private double value;

    @NotNull
    private boolean isRaining;

    @NotNull
    private SensorDTO sensor;

    @Min(-100)
    @Max(100)
    @NotNull
    public double getValue() {
        return value;
    }

    public void setValue(@Min(-100) @Max(100) @NotNull double value) {
        this.value = value;
    }

    @NotNull
    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(@NotNull boolean raining) {
        isRaining = raining;
    }

    public @NotNull SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(@NotNull SensorDTO sensor) {
        this.sensor = sensor;
    }
}
