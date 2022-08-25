package org.example.project1.DAO;

import org.example.project1.models.Book;
import org.example.project1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // show all books in table
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY book_id", new BeanPropertyRowMapper<>(Book.class));
    }
    // show one book for id
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    // add one book in table
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, book_author, book_year) VALUES (?, ?, ?)",
                book.getBookName(), book.getBookAuthor(), book.getBookYear());
    }
    // update one book in table
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET book_name = ?, book_author = ?, book_year = ? WHERE book_id = ?",
                updatedBook.getBookId(), updatedBook.getBookAuthor(), updatedBook.getBookYear(), id);
    }
    // get book owner by book id
    public Optional<Person> getBookOwnerByBookId(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.person_id WHERE Book.book_id = ?",
                new Object[]{bookId},
                new BeanPropertyRowMapper<Person>(Person.class)).stream().findAny();
    }

    // set book link to owner
    public void assignBookToOwner(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", selectedPerson.getPersonId(), id);
    }

    // set book free from owner
    public void releaseBookFromOwner(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", id);
    }

    // delete one book from table
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }
}
