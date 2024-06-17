package com.ups.oop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello world, this is my first Project!....";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name,
                        @RequestParam(required = false) String lastname,
                        @RequestParam(required = false) Integer age) {

        if (age != null) {
            return "This is my first rest service!, and my name is: " + name + " " + lastname + " and I'm " + age + " years old." ;
        } else if (lastname == null)
            return "This is my first rest service!, and my name is: " + name ;
        else {
            return "This is my first rest service!, and my name is: "
                    + name + " " + lastname  ;
        }
    }

    @GetMapping("/concat/{name}")
    public String concatenate(@PathVariable String name){
        return "This is my second rest service!, and my name is: "
                + name;
    }

    @GetMapping("/concat/{name}/{lastname}")
    public String concatenate(@PathVariable String name, @PathVariable String lastname){
        return "This is my second rest service!, and my name is: "
                + name + " " + lastname;
    }

    @GetMapping("/concat/{name}/{lastname}/{age}")
    public String concatenate(@PathVariable String name, @PathVariable String lastname, @PathVariable int age){
        return "This is my second rest service!, and my name is: "
                + name + " " + lastname +  " and I'm " + age + " years old.";
    }
}
