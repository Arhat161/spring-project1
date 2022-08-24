package org.example.project1.controllers;

import org.example.project1.DAO.BookDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    // from DAO/BookDAO we take BookDAO
    private final BookDAO bookDao;

    public BookController(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    // http://127.0.0.1/book
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allBooks", bookDao.index());
        return "book/index";
    }
}
