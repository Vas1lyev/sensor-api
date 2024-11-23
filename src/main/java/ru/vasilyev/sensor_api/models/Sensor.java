package ru.vasilyev.sensor_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String name) {
        this.name = name;
    }
}
