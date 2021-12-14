package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();
    private Map<Integer, AccidentType> typesMap = new HashMap<>();
    private Map<Integer, Rule> rulesMap = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public AccidentMem() {
        Set<Rule> rules1 = new HashSet<>();
        rules1.add(Rule.of(1, "Статья. 1"));
        rules1.add(Rule.of(3, "Статья. 3"));

        Set<Rule> rules2 = new HashSet<>();
        rules2.add(Rule.of(3, "Статья. 3"));

        Set<Rule> rules3 = new HashSet<>();
        rules3.add(Rule.of(2, "Статья. 2"));
        rules3.add(Rule.of(3, "Статья. 3"));

        int count1 = counter.incrementAndGet();
        accidents.put(count1, Accident.of(count1, "Иван", "Москва", "Д503ДД",
                "неправильная парковка", "Принята",
                AccidentType.of(2, "Машина и человек"), rules1));

        int count2 = counter.incrementAndGet();
        accidents.put(count2, Accident.of(count2, "Сергей", "Казань", "Р404РР",
                "затонирована задняя полусфера", "Отклонена",
                AccidentType.of(1, "Две машины"), rules2));

        int count3 = counter.incrementAndGet();
        accidents.put(count3, Accident.of(count3, "Антон", "Воронеж", "Н111НН",
                "отсутствие шипованных шин", "Завершена",
                AccidentType.of(3, "Машина и велосипед"), rules3));

        typesMap.put(1, AccidentType.of(1, "Две машины"));
        typesMap.put(2, AccidentType.of(2, "Машина и человек"));
        typesMap.put(3, AccidentType.of(3, "Машина и велосипед"));

        rulesMap.put(1, Rule.of(1, "Статья. 1"));
        rulesMap.put(2, Rule.of(2, "Статья. 2"));
        rulesMap.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        int id = counter.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public void edit(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getTypesList() {
        return typesMap.values();
    }

    public Collection<Rule> getRulesList() {
        return rulesMap.values();
    }

    public AccidentType getAccidentType(int typeId) {
        return typesMap.get(typeId);
    }

    public Set<Rule> getRuleSet(int[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        for (int id : rIds) {
            ruleSet.add(rulesMap.get(id));
        }
        return ruleSet;
    }
}
