package dwf.desafio1.Editorial.service;

import dwf.desafio1.Editorial.model.Book;
import dwf.desafio1.Editorial.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public Book addBook(Book book) {
        List<Book> books = repository.getAllBooks();
        books.add(book);
        repository.saveBooks(books);
        return book;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> books = repository.getAllBooks();
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean deleteBookById(int id) {
        List<Book> books = repository.getAllBooks();
        boolean removed = books.removeIf(b -> b.getId() == id);
        repository.saveBooks(books);
        return removed;
    }
}
