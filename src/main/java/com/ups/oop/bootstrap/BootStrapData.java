package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;

    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Person
        Person person1 = new Person();
        person1.setPersonId("0954248795");
        person1.setName("Cristina");
        person1.setLastname("Valenzuela");
        person1.setAge(21);

        Person person2 = new Person();
        person2.setPersonId("0920232816");
        person2.setName("Lizbeth");
        person2.setLastname("Chachipanta");
        person2.setAge(22);

        personRepository.save(person1);
        personRepository.save(person2);

        //Animals
        Animal animal1 = new Animal();
        animal1.setName("Alana");
        animal1.setBreed("Huskey");
        animal1.setColor("White");
        animal1.setWeight(15.5);
        animal1.setHeight(25);
        animal1.setLength(30);

        Animal animal2 = new Animal();
        animal2.setName("Apolo");
        animal2.setBreed("Yorkie");
        animal2.setColor("Black and Brown");
        animal2.setWeight(3.5);
        animal2.setHeight(5);
        animal2.setLength(0.9);

        animalRepository.save(animal1);
        animalRepository.save(animal2);

        System.out.println("--------- Started BootstrapData ---------");
        System.out.println("Number of Person: " +personRepository.count());
        System.out.println("Number of Animal: " +animalRepository.count());
    }
}