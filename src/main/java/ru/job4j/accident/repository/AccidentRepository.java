package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.type join fetch a.rulesSet order by a.id")
    List<Accident> getAccidents();
}