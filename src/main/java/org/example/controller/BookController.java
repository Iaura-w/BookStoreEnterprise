package org.example.controller;

import org.example.dto.BookDTO;
import org.example.entity.Autor;
import org.example.entity.Kategoria;
import org.example.entity.Ksiazka;
import org.example.services.AuthorService;
import org.example.services.BookService;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Ksiazka> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "bookslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Ksiazka book = new Ksiazka();
        model.addAttribute("book", book);
        return "addbookform";
    }

    @GetMapping("/formadd2")
    public String addForm2(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("bookDTO", bookDTO);
        List<Kategoria> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        List<Autor> authors =  authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "addbookform2";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Ksiazka ksiazka) {
        bookService.saveBook(ksiazka);
        return "redirect:/books/list";
    }

    @PostMapping("/saveBook2")
    public String saveBook2(@ModelAttribute("bookDTO") BookDTO bookDTO) {
//        Ksiazka ksiazka = bookService.getBook(bookDTO.getId());
//        if (ksiazka == null) {
//            ksiazka = new Ksiazka();
//        }
        Ksiazka ksiazka = new Ksiazka();
        ksiazka.setId(bookDTO.getId());
        ksiazka.setNazwa(bookDTO.getNazwa());
        ksiazka.setCena(bookDTO.getCena());
        ksiazka.setWydawnictwo(bookDTO.getWydawnictwo());
        ksiazka.setAutorzy(authorService.getAuthorsByIds(bookDTO.getAuthorsIds()));

        Kategoria kategoria = categoryService.getCategory(bookDTO.getKategoriaid());
        ksiazka.setKategoria(kategoria);

        bookService.saveBook(ksiazka);
        return "redirect:/books/list";
    }

    @GetMapping("/updateBookForm2")
    public String updateBookForm2(@RequestParam("bookId") int id, Model model) {
        Ksiazka ksiazka = bookService.getBook(id);
        BookDTO bookDTO = new BookDTO();
        if (ksiazka != null) {
            bookDTO.setId(ksiazka.getId());
            bookDTO.setNazwa(ksiazka.getNazwa());
            bookDTO.setWydawnictwo(ksiazka.getWydawnictwo());
            bookDTO.setCena(ksiazka.getCena());
            bookDTO.setKategoriaid(ksiazka.getKategoria().getId());
            bookDTO.setAuthorsIds(ksiazka.getAuthorsIds());
        }

        model.addAttribute("bookDTO", bookDTO);
        List<Kategoria> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        List<Autor> authors =  authorService.getAuthors();
        model.addAttribute("authors", authors);

        return "addbookform2";
    }

    @GetMapping("/deleteBook")
    public String deleteBookForm(@RequestParam("bookId") int id, Model model) {
        Ksiazka ksiazka = bookService.getBook(id);
        model.addAttribute("book",ksiazka);
        return "deletebookform";
    }
    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Ksiazka ksiazka){
        bookService.deleteBook(ksiazka);
        return "redirect:/books/list";
    }
}
