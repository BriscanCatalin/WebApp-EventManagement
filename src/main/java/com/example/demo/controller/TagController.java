package com.example.demo.controller;

import com.example.demo.dataRepository.TagRepository;
import com.example.demo.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllTags(Model model){
        model.addAttribute("title","All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayCreateTagsForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            model.addAttribute(tag);
            return "tags/create";
        }
        tagRepository.save(tag);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteTagsForm(Model model){
        model.addAttribute("title", "Delete Tag");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/delete";
    }

    @PostMapping("delete")
    public String processDeleteTagForm(@RequestParam(required = false) int[] tagIds){
        if(tagIds != null) {
            for (int id : tagIds) {
                tagRepository.deleteById(id);
            }
        }
        return "redirect:";
    }


    @GetMapping("update")
    public String displayUpdateTagForm(Model model){
        model.addAttribute("title", "Update Tag");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/update";
    }

    @PostMapping("update")
    public String processUpdateTagForm(@RequestParam(required = false) int[] tagIds) {
        if(tagIds != null) {
            for (int id : tagIds) {
                tagRepository.deleteById(id);
                return "redirect:create";
            }
        }
        return "redirect:";

    }


}
