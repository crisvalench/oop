package com.ups.oop.bootstrap;

import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = new Person();
        person1.setPersonId("0954278795");
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

        System.out.println("--------- Started BootstrapData ---------");
        System.out.println("Number of Person: " +personRepository.count());
    }
}
