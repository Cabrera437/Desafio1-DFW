package dwf.desafio1.Editorial.repository;

import dwf.desafio1.Editorial.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final String filePath = "src/main/resources/books.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Book> getAllBooks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveBooks(List<Book> books) {
        try {
            mapper.writeValue(new File(filePath), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
