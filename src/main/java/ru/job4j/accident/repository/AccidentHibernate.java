package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Accident> getAccidents() {
        try (Session session = sf.openSession()) {
            Query query = session.createQuery(
                    "select distinct a from Accident a join fetch a.type "
                    + "join fetch a.rulesSet"
            );
            return query.list();
        }
    }

    public void create(Accident accident) {
        for (Rule rule : accident.getRules()) {
            System.out.println(rule.getName());
        }
        try (Session session = sf.openSession()) {
            session.save(accident);
        }
    }

    public void edit(Accident accident) {
        try (Session session = sf.openSession()) {
            session.update(accident);
        }
    }

    public Accident findById(int id) {
        List<Accident> list = new ArrayList<>();
        try (Session session = sf.openSession()) {
            Query query = session.createQuery(
                    "select distinct a from Accident a where a.id = :idParam"
            );
            query.setParameter("idParam", id);
            list = query.list();
        }
        Accident accident = null;
        if (list.size() > 0) {
            accident = list.get(0);
        }
        return accident;
    }

    public Map<Integer, Rule> getRulesMap() {
        Map<Integer, Rule> rulesMap = new HashMap<>();
        List<Rule> list;
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("select distinct r from Rule r");
            list = query.list();
        }
        for (Rule rule : list) {
            rulesMap.put(rule.getId(), rule);
        }
        return rulesMap;
    }

    public Map<Integer, AccidentType> getTypesMap() {
        Map<Integer, AccidentType> typesMap = new HashMap<>();
        List<AccidentType> list;
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("select distinct actype from AccidentType actype");
            list = query.list();
        }
        for (AccidentType type : list) {
            typesMap.put(type.getId(), type);
        }
        return typesMap;
    }

    public AccidentType getAccidentType(int typeId) {
        return getTypesMap().get(typeId);
    }

    public Set<Rule> getRuleSet(int[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        Map<Integer, Rule> rulesMap = getRulesMap();
        for (int id : rIds) {
            ruleSet.add(rulesMap.get(id));
        }
        return ruleSet;
    }
}