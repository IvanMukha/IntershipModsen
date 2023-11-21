package test.Modsen.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import test.Modsen.dao.LibraryDAO;

@Controller
public class LibraryController {
    private final LibraryDAO libraryDAO;

    @Autowired
    public LibraryController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;

    }



    @GetMapping("/books/freebooks")
    public String index(Model model) {
        model.addAttribute("freebooks",libraryDAO.getFreeBooks() );
        return "books/freeBook";
    }
}
