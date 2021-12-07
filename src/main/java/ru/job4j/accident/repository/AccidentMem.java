package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();
    private int counter = 1;

    public AccidentMem() {
        Accident accident1 = Accident.of(1, "Иван", "Москва", "Д503ДД",
                "неправильная парковка", "Принята",
                AccidentType.of(1, "Машина и человек"));
        Accident accident2 = Accident.of(2, "Сергей", "Казань", "Р404РР",
                "затонирована задняя полусфера", "Отклонена",
                AccidentType.of(1, "Две машины"));
        Accident accident3 = Accident.of(3, "Антон", "Воронеж", "Н111НН",
                "отсутствие шипованных шин", "Завершена",
                AccidentType.of(1, "Машина и велосипед"));
        accidents.put(counter++, accident1);
        accidents.put(counter++, accident2);
        accidents.put(counter++, accident3);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        int id = counter++;
        accident.setId(id);
        accidents.put(id, accident);
    }

    public void edit(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Map<Integer, AccidentType> getTypes() {
        Map<Integer, AccidentType> typesMap = new HashMap<>();
        typesMap.put(1, AccidentType.of(1, "Две машины"));
        typesMap.put(2, AccidentType.of(2, "Машина и человек"));
        typesMap.put(3, AccidentType.of(3, "Машина и велосипед"));
        return typesMap;
    }
}
