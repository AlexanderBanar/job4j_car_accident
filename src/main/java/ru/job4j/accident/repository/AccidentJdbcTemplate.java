package ru.job4j.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    @Autowired
    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> getAccidents() {
        SortedMap<Integer, Accident> sortedAccidentMap = new TreeMap<>();
        Map<Integer, Set<Rule>> rulesMap = getDefinedRulesMap();
        SqlRowSet rs = jdbc.queryForRowSet(
                "select * from auto_crash.public.accident as ac join auto_crash.public.types as typ "
                        + "on ac.type_id = typ.id");
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setAddress(rs.getString("address"));
            accident.setNumber(rs.getString("number"));
            accident.setDescription(rs.getString("description"));
            accident.setStatus(rs.getString("status"));
            accident.setType(AccidentType.of(
                    rs.getInt(8),
                    rs.getString(9)
            ));
            accident.setRules(rulesMap.get(rs.getInt("id")));
            sortedAccidentMap.put(rs.getInt("id"), accident);
        }
        return sortedAccidentMap.values();
    }

    private Map<Integer, Set<Rule>> getDefinedRulesMap() {
        Map<Integer, Set<Rule>> rulesDefinedMap = new HashMap<>();
        Map<Integer, Rule> rulesMap = getRulesMap();
        SqlRowSet rs = jdbc.queryForRowSet("select * from auto_crash.public.accidents_rules");
        while (rs.next()) {
            int id = rs.getInt("accident_id");
            if (!rulesDefinedMap.containsKey(id)) {
                Set<Rule> rulesForKey = new HashSet<>();
                rulesDefinedMap.put(id, rulesForKey);
            }
            Set<Rule> rulesSet = rulesDefinedMap.get(id);
            rulesSet.add(rulesMap.get(rs.getInt("rule_id")));
        }
        return rulesDefinedMap;
    }

    public void create(Accident ac) {
        String sql = "insert into auto_crash.public.accident(name, address, number, description, status, type_id) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s', %d)"
                .formatted(ac.getName(), ac.getAddress(), ac.getNumber(), ac.getDescription(),
                        ac.getStatus(), ac.getType().getId());
        jdbc.update(sql);
        String sql2 = "select * from auto_crash.public.accident where number = '%s'"
                .formatted(ac.getNumber());
        SqlRowSet rs = jdbc.queryForRowSet(sql2);
        int acId = 0;
        if (rs.first()) {
            acId = rs.getInt("id");
        }
        insertRules(acId, ac.getRules());
    }

    public void edit(Accident ac) {
        String sql1 = ("update auto_crash.public.accident set name = '%s', address = '%s', "
                + "number = '%s', description = '%s', status = '%s', type_id = '%d' where id = %d")
                .formatted(ac.getName(), ac.getAddress(), ac.getNumber(), ac.getDescription(),
                        ac.getStatus(), ac.getType().getId(), ac.getId());
        jdbc.update(sql1);
        String sql2 = "delete from auto_crash.public.accidents_rules where accident_id = %d"
                .formatted(ac.getId());
        jdbc.update(sql2);
        insertRules(ac.getId(), ac.getRules());
    }

    private void insertRules(int accidentId, Set<Rule> accidentRuleSet) {
        for (Rule rule : accidentRuleSet) {
            String sql3 = "insert into auto_crash.public.accidents_rules(accident_id, rule_id) "
                    + "values (%d, %d)"
                            .formatted(accidentId, rule.getId());
            jdbc.update(sql3);
        }
    }

    public Accident findById(int id) {
        Accident resultAc = new Accident();
        String sql = ("select name, address, number, description, status "
                + "from auto_crash.public.accident where id = %d")
                .formatted(id);
        SqlRowSet rs = jdbc.queryForRowSet(sql);
        if (rs.first()) {
            resultAc.setId(id);
            resultAc.setName(rs.getString("name"));
            resultAc.setAddress(rs.getString("address"));
            resultAc.setNumber(rs.getString("number"));
            resultAc.setDescription(rs.getString("description"));
            resultAc.setStatus(rs.getString("status"));
        }
        return resultAc;
    }

    public Map<Integer, AccidentType> getTypesMap() {
        Map<Integer, AccidentType> typesMap = new HashMap<>();
        SqlRowSet rs = jdbc.queryForRowSet("select * from auto_crash.public.types");
        while (rs.next()) {
            typesMap.put(rs.getInt("id"), AccidentType.of(
                    rs.getInt("id"),
                    rs.getString("name")
            ));
        }
        return typesMap;
    }

    public Map<Integer, Rule> getRulesMap() {
        Map<Integer, Rule> rulesMap = new HashMap<>();
        SqlRowSet rs = jdbc.queryForRowSet("select * from auto_crash.public.rules");
        while (rs.next()) {
            rulesMap.put(rs.getInt("id"),
                    Rule.of(
                            rs.getInt("id"),
                            rs.getString("name")
            ));
        }
        return rulesMap;
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
