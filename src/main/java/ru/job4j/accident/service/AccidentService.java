package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {
    private AccidentJdbcTemplate accidentJdbcTemplate;

    @Autowired
    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> getAccidents() {
        return accidentJdbcTemplate.getAccidents();
    }

    public void create(Accident accident) {
        accidentJdbcTemplate.create(accident);
    }

    public void edit(Accident accident) {
        accidentJdbcTemplate.edit(accident);
    }

    public Accident findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }
}