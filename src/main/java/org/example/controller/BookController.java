package org.example.controller;

import org.example.dto.BookDTO;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Category;
import org.example.services.AuthorService;
import org.example.services.BookService;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "bookslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addbookform";
    }

    @GetMapping("/formadd2")
    public String addForm2(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("bookDTO", bookDTO);
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "addbookform2";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @PostMapping("/saveBook2")
    public String saveBook2(@ModelAttribute("bookDTO") BookDTO bookDTO) {
//        Book book = bookService.getBook(bookDTO.getId());
//        if (book == null) {
//            book = new Book();
//        }
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());
        book.setAuthors(authorService.getAuthorsByIds(bookDTO.getAuthorsIds()));

        Category category = categoryService.getCategory(bookDTO.getCategoryid());
        book.setCategory(category);

        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/updateBookForm2")
    public String updateBookForm2(@RequestParam("bookId") int id, Model model) {
        Book book = bookService.getBook(id);
        BookDTO bookDTO = new BookDTO();
        if (book != null) {
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setPublisher(book.getPublisher());
            bookDTO.setPrice(book.getPrice());
            bookDTO.setCategoryid(book.getCategory().getId());
            bookDTO.setAuthorsIds(book.getAuthorsIds());
        }

        model.addAttribute("bookDTO", bookDTO);
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);

        return "addbookform2";
    }

    @GetMapping("/deleteBook")
    public String deleteBookForm(@RequestParam("bookId") int id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "deletebookform";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.deleteBook(book);
        return "redirect:/books/list";
    }
}
