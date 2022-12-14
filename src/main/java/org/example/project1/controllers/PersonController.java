package org.example.project1.controllers;

import org.example.project1.DAO.PersonDAO;
import org.example.project1.models.Person;
import org.example.project1.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    // from DAO/PersonDAO we take PersonDAO
    private final PersonDAO personDao;
    private final PersonValidator personValidator;

    public PersonController(PersonDAO personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    // http://127.0.0.1/people
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    // http://127.0.0.1/people/5
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        model.addAttribute("allBooks", personDao.getBooksByPersonId(id));
        return "people/show";
    }

    // http://127.0.0.1/people/new
    // show form
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    // http://127.0.0.1/people/new -> click on "Create new Person" -> http://127.0.0.1/people
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return "people/new";
        }
        personDao.save(person);
        return "redirect:/people";
    }

    // http://127.0.0.1/5/edit -> click on "Edit" -> http://127.0.0.1/edit -> fill values and show form
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    // http://127.0.0.1/edit -> click on "Update" -> http://127.0.0.1/people
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDao.update(id, person);
        return "redirect:/people";
    }

    // http://127.0.0.1/people/5 -> click on "DELETE" -> http://127.0.0.1/people
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }

}
