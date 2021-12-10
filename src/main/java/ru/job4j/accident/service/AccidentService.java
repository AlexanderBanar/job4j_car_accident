package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Map;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    @Autowired
    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void edit(Accident accident) {
        accidentMem.edit(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public Collection<AccidentType> getTypes() {
        return accidentMem.getTypesList();
    }

    public Collection<Rule> getRules() {
        return accidentMem.getRulesList();
    }
}
