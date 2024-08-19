package com.ups.oop.controller;

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

    public TemplateController(PersonService personService, AnimalService animalService, BookService bookService, ClientService clientService, WorkersService workersService) {
        this.personService = personService;
        this.animalService = animalService;
        this.bookService = bookService;
        this.clientService = clientService;
        this.workersService = workersService;
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
        model.addAttribute("books", bookService.getBookAndAuthors());
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
}
