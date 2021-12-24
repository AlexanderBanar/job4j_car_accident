package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String number;
    private String description;
    private String status;

    @OneToOne(cascade = {CascadeType.ALL})
    private AccidentType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<Rule> rulesSet = new HashSet<>();

    public static Accident of(int id, String name, String address, String number, String description, String status,
                              AccidentType type, Set<Rule> rulesSet) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.address = address;
        accident.number = number;
        accident.description = description;
        accident.status = status;
        accident.type = type;
        accident.rulesSet = rulesSet;
        return accident;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rulesSet;
    }

    public void setRules(Set<Rule> rulesSet) {
        this.rulesSet = rulesSet;
    }

    public void addRule(Rule rule) {
        this.rulesSet.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}