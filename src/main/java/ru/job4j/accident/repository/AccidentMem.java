package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();
    private int counter = 1;

    public AccidentMem() {
        Accident accident1 = Accident.of(1, "Иван", "Москва", "Д503ДД",
                "неправильная парковка", "Принята");
        Accident accident2 = Accident.of(2, "Сергей", "Казань", "Р404РР",
                "затонирована задняя полусфера", "Отклонена");
        Accident accident3 = Accident.of(3, "Антон", "Воронеж", "Н111НН",
                "отсутствие шипованных шин", "Завершена");
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
}
