package test.Modsen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import test.Modsen.dao.BookDAO;
import test.Modsen.dao.LibraryDAO;
import test.Modsen.dao.PersonDAO;
import test.Modsen.models.Book;
import test.Modsen.models.BookRentalInfo;
import test.Modsen.models.Person;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final LibraryDAO libraryDAO;



    @Autowired
    public BooksController(BookDAO BookDAO, PersonDAO personDAO, LibraryDAO libraryDAO) {
        this.bookDAO = BookDAO;
        this.personDAO = personDAO;

        this.libraryDAO = libraryDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        model.addAttribute("isbnNotFound",false);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        List<BookRentalInfo> rentBooks = libraryDAO.show(id);

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
            model.addAttribute("rentbooks",libraryDAO.show(id));
        }
        else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book Book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book Book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(Book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

           bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        libraryDAO.release(id);
        System.out.println(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        libraryDAO.assign(id);

        return "redirect:/books/" + id;
    }
    @GetMapping("/findByISBN")
    public String findByISBN(@RequestParam("ISBN") String ISBN, Model model) {
        Book foundbook=bookDAO.findByISBN(ISBN);
        if(foundbook!=null){
            return "redirect:/books/" + foundbook.getId();
        }
        else{ model.addAttribute("isbnNotFound", true);
            model.addAttribute("books", bookDAO.index());}
            return "books/index";
    }
    @GetMapping("/findById")
    public String findById(@RequestParam("id")int id, Model model) {
        Book foundbook=bookDAO.show(id);
        if(foundbook!=null){
            return "redirect:/books/" + foundbook.getId();
        }
        else{ model.addAttribute("idNotFound", true);
            model.addAttribute("books", bookDAO.index());}
        return "books/index";
    }

}



