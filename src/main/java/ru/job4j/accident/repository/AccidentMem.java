package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    Map<Integer, Accident> accidents = initMem();

    private Map<Integer, Accident> initMem() {
        Map<Integer, Accident> result = new HashMap<>();
        Accident accident1 = Accident.of(1, "Иван", "Москва", "Д503ДД",
                "неправильная парковка", "Принята");
        Accident accident2 = Accident.of(2, "Сергей", "Казань", "Р404РР",
                "затонирована задняя полусфера", "Отклонена");
        Accident accident3 = Accident.of(3, "Антон", "Воронеж", "Н111НН",
                "отсутствие шипованных шин", "Завершена");
        result.put(1, accident1);
        result.put(2, accident2);
        result.put(3, accident3);
        return result;
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(Map<Integer, Accident> accidents) {
        this.accidents = accidents;
    }
}
