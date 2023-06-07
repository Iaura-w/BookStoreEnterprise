package org.example.controller;

import org.example.entity.Kategoria;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listCategories(Model model) {
        List<Kategoria> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categorylist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Kategoria category = new Kategoria();
        model.addAttribute("category", category);
        return "addcategoryform";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Kategoria kategoria) {
        categoryService.addCategory(kategoria);
        return "redirect:/categories/list";
    }
}
