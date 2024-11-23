package ru.vasilyev.sensor_api.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MeasurementDAO {
    private JdbcTemplate jdbcTemplate;

    public MeasurementDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRainyDaysCount() {
        String sql = "select count(*) from measurement where raining = true";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
