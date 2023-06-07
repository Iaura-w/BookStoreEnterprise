package org.example.controller;

import org.example.entity.Autor;
import org.example.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model model) {
        List<Autor> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "authorlist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Autor author = new Autor();
        model.addAttribute("author", author);
        return "addauthorform";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("author") Autor autor) {
        authorService.saveAuthor(autor);
        return "redirect:/authors/list";
    }
}
