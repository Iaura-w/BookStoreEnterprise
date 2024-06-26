package org.example.controller;

import org.example.entity.Author;
import org.example.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "authorlist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "addauthorform";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors/list";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthorForm(@RequestParam("authorId") int id, Model model) {
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return "deleteauthorform";
    }

    @PostMapping("/deleteAuthor")
    public String deleteAuthor(@ModelAttribute("author") Author author) {
        authorService.deleteAuthor(author);
        return "redirect:/authors/list";
    }

    @GetMapping("/updateAuthorForm")
    public String updateBookForm(@RequestParam("authorId") int id, Model model) {
        Author author = authorService.getAuthor(id);
        if (author == null) {
            author = new Author();
        }
        model.addAttribute("author", author);
        return "addauthorform";
    }
}
