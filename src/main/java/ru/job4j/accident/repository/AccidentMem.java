package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = Accident.of(1, "Иван", "Москва", "Д503ДД",
                "неправильная парковка", "Принята");
        Accident accident2 = Accident.of(2, "Сергей", "Казань", "Р404РР",
                "затонирована задняя полусфера", "Отклонена");
        Accident accident3 = Accident.of(3, "Антон", "Воронеж", "Н111НН",
                "отсутствие шипованных шин", "Завершена");
        accidents.put(1, accident1);
        accidents.put(2, accident2);
        accidents.put(3, accident3);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}
