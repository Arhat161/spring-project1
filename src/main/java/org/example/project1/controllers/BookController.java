package org.example.project1.controllers;

import org.example.project1.DAO.BookDAO;
import org.example.project1.DAO.PersonDAO;
import org.example.project1.models.Book;
import org.example.project1.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    // from DAO/BookDAO we take BookDAO
    private final BookDAO bookDao;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    // http://127.0.0.1/book
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allBooks", bookDao.index());
        return "book/index";
    }

    // http://127.0.0.1/book/5
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        // add information about one book
        model.addAttribute("book", bookDao.show(id));
        // get book owner
        Optional<Person> bookOwner = bookDao.getBookOwnerByBookId(id);

        if (bookOwner.isPresent()) {
            // return owner details
            model.addAttribute("bookOwner", bookOwner.get());
        } else {
            // return list of all people
            model.addAttribute("people", personDAO.index());
        }
        return "book/show";
    }

    // http://127.0.0.1/book/new
    // show form
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    // http://127.0.0.1/book/new -> click on "Create new Book" -> http://127.0.0.1/book
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return "book/new";
        }
        bookDao.save(book);
        return "redirect:/book";
    }

    // http://127.0.0.1/book/5 -> click on "Edit" -> http://127.0.0.1/edit -> fill values and show form
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "book/edit";
    }

    // http://127.0.0.1/book/5/edit -> click on "Update" -> http://127.0.0.1/book
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }

        bookDao.update(id, book);
        return "redirect:/book";
    }

    // http://127.0.0.1/book/5 -> click on "DELETE" -> http://127.0.0.1/book
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/book";
    }
    // http://127.0.0.1/book/5 -> select owner -> click on "assign book" -> http://127.0.0.1/book/5
    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDao.assignBookToOwner(id, selectedPerson);
        return "redirect:/book/{id}";
    }
    // http://127.0.0.1/book/5 -> click on "release book" -> http://127.0.0.1/book/5
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookDao.releaseBookFromOwner(id);
        return "redirect:/book/{id}";
    }

}
