package com.example.demo.controller;

import com.example.demo.dataRepository.EventCategoryRepository;
import com.example.demo.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllCategories(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String displayCreateCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteCategoryForm(Model model){
        model.addAttribute("title", "Delete Category");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventCategoryForm(@RequestParam(required = false) int[] categoryIds){
        if(categoryIds != null) {
            for (int id : categoryIds) {
                eventCategoryRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("update")
    public String displayUpdateEventCategoryForm(Model model){
        model.addAttribute("title", "Update Category");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/update";
    }

    @PostMapping("update")
    public String processUpdateEventCategoryForm(@RequestParam(required = false) int[] categoryIds) {
        if(categoryIds != null) {
            for (int id : categoryIds) {
                eventCategoryRepository.deleteById(id);
                return "redirect:create";
            }
        }
        return "redirect:";

    }



}
