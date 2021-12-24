package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

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
        accident.setType(service.getAccidentType(typeId));
        accident.setStatus("Принята");
        service.create(accident, rIds);
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
        accident.setType(service.getAccidentType(typeId));
        accident.setRules(service.getRuleSet(rIds));
        accident.setStatus("Принята");
        service.edit(accident);
        return "redirect:/";
    }
}
