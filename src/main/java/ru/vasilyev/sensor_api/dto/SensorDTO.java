package ru.vasilyev.sensor_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    public @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name shouldn't be empty")
                        @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
                        String name) {
        this.name = name;
    }
}
