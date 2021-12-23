package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Set;

@Service
public class AccidentService {
    private AccidentHibernate accidentHibernate;

    @Autowired
    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Collection<Accident> getAccidents() {
        return accidentHibernate.getAccidents();
    }

    public void create(Accident accident) {
        accidentHibernate.create(accident);
    }

    public void edit(Accident accident) {
        accidentHibernate.edit(accident);
    }

    public Accident findById(int id) {
        return accidentHibernate.findById(id);
    }

    public Collection<Rule> getRules() {
        return accidentHibernate.getRulesMap().values();
    }

    public Collection<AccidentType> getTypes() {
        return accidentHibernate.getTypesMap().values();
    }

    public AccidentType getAccidentType(int typeId) {
        return accidentHibernate.getAccidentType(typeId);
    }

    public Set<Rule> getRuleSet(int[] rIds) {
        return accidentHibernate.getRuleSet(rIds);
    }
}