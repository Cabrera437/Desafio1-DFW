package dwf.desafio1.Editorial.controller;

import dwf.desafio1.Editorial.model.Book;
import dwf.desafio1.Editorial.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean removed = service.deleteBookById(id);
        return removed ? "Book removed successfully." : "Book not found.";
    }
}
