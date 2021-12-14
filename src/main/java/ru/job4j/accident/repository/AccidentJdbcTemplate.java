package ru.job4j.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    @Autowired
    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> getAccidents() {
        Collection<Accident> collection = new ArrayList<>();
        SqlRowSet rs = jdbc.queryForRowSet("select * from auto_crash.public.accident");
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setAddress(rs.getString("address"));
            accident.setNumber(rs.getString("number"));
            accident.setDescription(rs.getString("description"));
            accident.setStatus(rs.getString("status"));
            System.out.println(accident.getName() + " " + accident.getNumber());
            collection.add(accident);
        }
        return collection;
    }

    public void create(Accident ac) {
        StringBuilder sb = new StringBuilder(
                "insert into auto_crash.public.accident(name, address, number, description, status) VALUES ('");
        sb.append(ac.getName()).append("', '");
        sb.append(ac.getAddress()).append("', '");
        sb.append(ac.getNumber()).append("', '");
        sb.append(ac.getDescription()).append("', '");
        sb.append(ac.getStatus()).append("')");
        jdbc.update(sb.toString());
    }

    public void edit(Accident ac) {
        StringBuilder sb = new StringBuilder(
                "update auto_crash.public.accident set name = '");
        sb.append(ac.getName()).append("', address = '");
        sb.append(ac.getAddress()).append("', number = '");
        sb.append(ac.getNumber()).append("', description = '");
        sb.append(ac.getDescription()).append("', status = '");
        sb.append(ac.getStatus()).append("' where id = ");
        sb.append(ac.getId());
        jdbc.update(sb.toString());
    }

    public Accident findById(int id) {
        Accident resultAc = new Accident();
        StringBuilder sb = new StringBuilder(
                "select name, address, number, description, status from auto_crash.public.accident where id = ");
        sb.append(id);
        SqlRowSet rs = jdbc.queryForRowSet(sb.toString());
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
}
