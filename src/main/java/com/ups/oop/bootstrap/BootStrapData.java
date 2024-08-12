package com.ups.oop.bootstrap;

import com.ups.oop.entity.*;
import com.ups.oop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository, StudentRepository studentRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.studentRepository = studentRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        Person person3 = new Person();
        person3.setPersonId("0960459076");
        person3.setName("Omayra");
        person3.setLastname("Alvarado");
        person3.setAge(21);

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);

        //Animals
        Animal animal1 = new Animal();
        animal1.setPetName("Alana");
        animal1.setName("Dog");
        animal1.setBreed("Huskey");
        animal1.setColor("White");
        animal1.setWeight(15.5);
        animal1.setHeight(25);
        animal1.setLength(30);

        Animal animal2 = new Animal();
        animal2.setPetName("Apolo");
        animal2.setName("Dog");
        animal2.setBreed("Yorkie");
        animal2.setColor("Black and Brown");
        animal2.setWeight(3.5);
        animal2.setHeight(5);
        animal2.setLength(0.9);

        Animal animal3 = new Animal();
        animal3.setPetName("Kira");
        animal3.setName("Dog");
        animal3.setBreed("Bulldog");
        animal3.setColor("Black and White");
        animal3.setWeight(5.3);
        animal3.setHeight(8);
        animal3.setLength(10);

        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(animal3);

        //Student

        Student student1 = new Student();
        student1.setStudentId("123");
        student1.setName("María");
        student1.setLastname("Ramirez");

        studentRepository.save(student1);


        //Autor & Book (1)
        Author author1 = new Author();
        author1.setName("Michel");
        author1.setLastName("Foucault");
        authorRepository.save(author1);

        Book book1 = new Book();
        book1.setTitle("Desolatia");
        book1.setEditorial("Amarante");
        book1.setAuthor(author1);
        bookRepository.save(book1);

        Book book4 = new Book();
        book4.setTitle("Los 3 MOsqueteros");
        book4.setEditorial("S.A Editorial");
        book4.setAuthor(author1);
        bookRepository.save(book4);

        author1.getBooks().add(book4);
        author1.getBooks().add(book1);
        authorRepository.save(author1);

        //Autor & Book (2)
        Author author2 = new Author();
        author2.setName("Helen");
        author2.setLastName("Hester");
        authorRepository.save(author2);

        Book book2 = new Book();
        book2.setTitle("El mundo de Elea");
        book2.setEditorial("Martínez Roca");
        book2.setAuthor(author2);
        bookRepository.save(book2);

        author2.getBooks().add(book2);
        authorRepository.save(author2);

        //Autor & Book (3)
        Author author3 = new Author();
        author3.setName("Patricia");
        author3.setLastName("Sadovsky");
        authorRepository.save(author3);

        Book book3 = new Book();
        book3.setTitle("Ciencia Políticas");
        book3.setEditorial("Ariel");
        book3.setAuthor(author3);
        bookRepository.save(book3);

        author3.getBooks().add(book3);
        authorRepository.save(author3);


        System.out.println("--------- Started BootstrapData ---------");
        System.out.println("Number of Person: " +personRepository.count());
        System.out.println("Number of Animal: " +animalRepository.count());
        System.out.println("Number of Student: " +studentRepository.count());
    }
}
