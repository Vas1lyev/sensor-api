package ru.vasilyev.sensor_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @Min(-100)
    @Max(100)
    @NotNull
    private double value;

    @Column(name = "raining")
    @NotNull
    private boolean isRaining;

    @Column(name = "recorded_at")
    @NotNull
    private LocalDateTime recordedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(double value, boolean raining, LocalDateTime recordedAt, Sensor sensor) {
        this.value = value;
        this.isRaining = raining;
        this.recordedAt = recordedAt;
        this.sensor = sensor;
    }

    public Measurement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        this.isRaining = raining;
    }

    public @NotNull LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(@NotNull LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public @NotNull Sensor getSensor() {
        return sensor;
    }

    public void setSensor(@NotNull Sensor sensor) {
        this.sensor = sensor;
    }
}


