package com.ups.oop.controller;

import com.ups.oop.entity.Loan;
import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    private final PersonService personService;
    private final AnimalService animalService;
    private final BookService bookService;
    private final ClientService clientService;
    private final WorkersService workersService;
    private final LoanService loanService;
    private final LoanDetailService loanDetailService;

    public TemplateController(PersonService personService, AnimalService animalService, BookService bookService, ClientService clientService, WorkersService workersService, LoanService loanService, LoanDetailService loanDetailService) {
        this.personService = personService;
        this.animalService = animalService;
        this.bookService = bookService;
        this.clientService = clientService;
        this.workersService = workersService;
        this.loanService = loanService;
        this.loanDetailService = loanDetailService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model){
        return"template";
    }

    @GetMapping("/people")
    public String getPeople(Model model){
        model.addAttribute("people",  personService.getPeople());
        return "person/list";
    }

    @GetMapping("/animals")
    public String getAminals(Model model){
        model.addAttribute("animals",  animalService.getAnimals());
        return "animals/list";
    }

    @GetMapping("/books")
    public String getBookAndAuthors(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
    @GetMapping("/clients")
    public String getClients(Model model){
        model.addAttribute("clients",  clientService.getClients());
        return "clients/list";
    }

    @GetMapping("/workers")
    public String getWorkers(Model model){
        model.addAttribute("workers",  workersService.getWorkers());
        return "workers/list";
    }

    @GetMapping("/loans")
    public String getLoans(Model model){
        model.addAttribute("loans",  loanService.getLoans());
        return "loan/list";
    }

    @GetMapping("/loans-details")
    public String getLoansDetails(Model model){
        model.addAttribute("loansDetails",  loanDetailService.getLoanDetail());
        return "loan-details/list";
    }
}
