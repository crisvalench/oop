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
    public String hello(@RequestParam(required = false) String name,
                        @RequestParam(required = false) String lastname,
                        @RequestParam(required = false) Integer age) {

        if (name != null & lastname != null & age != null){
            return "This is my first rest service and my name is: " + name + " " + lastname + " and my age is " + age + " years old.";
        }
        if (name != null & lastname != null) {
            return "This is my first rest service and my name is: " + name + " " + lastname ;
        }
        if (name != null & age  != null) {
            return "This is my first rest service and my name is: " + name + " and my age is " + age + " years old.";
        }
        if (lastname != null & age  != null) {
            return "This is my first rest service and my lastname is: " + lastname + " and my age is " + age + " years old.";
        }
        if (name != null) {
            return "This is my first rest service! and my name is: " +name ;
        }
        if (lastname != null) {
            return "This is my first rest service!, and my lastname is: " + lastname ;
        }
        if (age != null){
            return "This is my first rest service!, and my age is " + age;
        }
        return "This is my first rest service!" ;
    }

    @GetMapping("/concat")
    public String concatenate(){
        return "This is my second rest service!";
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
