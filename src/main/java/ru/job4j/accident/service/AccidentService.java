package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccidentService {
    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;
    private RuleRepository ruleRepository;

    @Autowired
    public AccidentService(AccidentRepository accidentRepository,
                           AccidentTypeRepository accidentTypeRepository,
                           RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<Accident> getAccidents() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    public void create(Accident accident, int[] rIds) {
        accident.setRules(getRuleSet(rIds));
        accidentRepository.save(accident);
    }

    public void edit(Accident accident) {
        accidentRepository.save(accident);
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id).orElse(null);
    }

    public Collection<Rule> getRules() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

    public Collection<AccidentType> getTypes() {
        return (Collection<AccidentType>) accidentTypeRepository.findAll();
    }

    public AccidentType getAccidentType(int typeId) {
        return accidentTypeRepository.findById(typeId).orElse(null);
    }

    public Set<Rule> getRuleSet(int[] rIds) {
        List<Integer> idsList = IntStream.of(rIds).boxed().collect(Collectors.toList());
        Set<Rule> ruleSet = new HashSet<>();
        for (Rule rule : ruleRepository.findAllById(idsList)) {
            ruleSet.add(rule);
        }
        return ruleSet;
    }
}