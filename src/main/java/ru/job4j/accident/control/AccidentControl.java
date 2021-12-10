package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentService service;

    @Autowired
    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("type.id") int typeId, @RequestParam("rIds") int[] rIds,
                       @ModelAttribute Accident accident) {
        accident.setType(getAccidentType(typeId));
        accident.setRules(getRuleSet(rIds));
        accident.setStatus("Принята");
        service.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("rules", service.getRules());
        model.addAttribute("types", service.getTypes());
        return "accident/update";
    }

    @PostMapping("/updateSave")
    public String updateSave(@RequestParam("type.id") int typeId, @RequestParam("rIds") int[] rIds,
                             @ModelAttribute Accident accident) {
        accident.setType(getAccidentType(typeId));
        accident.setRules(getRuleSet(rIds));
        accident.setStatus("Принята");
        service.edit(accident);
        return "redirect:/";
    }

    private AccidentType getAccidentType(int typeId) {
        AccidentType accidentType = new AccidentType();
        for (AccidentType ac : service.getTypes()) {
            if (ac.getId() == typeId) {
                accidentType = ac;
            }
        }
        return accidentType;
    }

    private Set<Rule> getRuleSet(int[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        for (int id : rIds) {
            for (Rule rule : service.getRules()) {
                if (rule.getId() == id) {
                    ruleSet.add(rule);
                }
            }
        }
        return ruleSet;
    }
}
