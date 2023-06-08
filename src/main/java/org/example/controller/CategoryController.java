package org.example.controller;

import org.example.entity.Category;
import org.example.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categorylist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "addcategoryform";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategoryForm(@RequestParam("categoryId") int id, Model model) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        return "deletecategoryform";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.deleteCategory(category);
        return "redirect:/categories/list";
    }
}
