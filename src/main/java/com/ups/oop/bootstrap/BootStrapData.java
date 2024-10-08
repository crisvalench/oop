package com.ups.oop.bootstrap;


import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Author;
import com.ups.oop.entity.Book;
import com.ups.oop.entity.Client;
import com.ups.oop.entity.Editorial;
import com.ups.oop.entity.Loan;
import com.ups.oop.entity.LoanDetail;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Student;
import com.ups.oop.entity.Worker;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.AuthorRepository;
import com.ups.oop.repository.BookRepository;
import com.ups.oop.repository.ClientRepository;
import com.ups.oop.repository.EditorialRepository;
import com.ups.oop.repository.LoanDetailRepository;
import com.ups.oop.repository.LoanRepository;
import com.ups.oop.repository.PersonRepository;
import com.ups.oop.repository.StudentRepository;
import com.ups.oop.repository.WorkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final EditorialRepository editorialRepository;
    private final LoanRepository loanRepository;
    private final LoanDetailRepository loanDetailRepository;


    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository, StudentRepository studentRepository, AuthorRepository authorRepository, BookRepository bookRepository, ClientRepository clientRepository, WorkerRepository workerRepository, EditorialRepository editorialRepository, LoanRepository loanRepository, LoanDetailRepository loanDetailRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.studentRepository = studentRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
        this.editorialRepository = editorialRepository;
        this.loanRepository = loanRepository;
        this.loanDetailRepository = loanDetailRepository;
    }

        //Person
        public void createPeople() {
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
        }
        //Animals
        public void createAnimals() {
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
        }
        //Student
        public void createStudents() {
            Student student1 = new Student();
            student1.setStudentId("123");
            student1.setName("María");
            student1.setLastname("Ramirez");

            studentRepository.save(student1);
        }
        public void createBooksAuthorsAndEditorials() {
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
            book4.setTitle("Los 3 Mosqueteros");
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

            //Editorials
            Editorial e1 = new Editorial();
            e1.setName("Pearson");
            e1.getBooks().add(book1);
            e1.getBooks().add(book2);
            e1.getBooks().add(book4);
            editorialRepository.save(e1);

            Editorial e2 = new Editorial();
            e2.setName("LNS");
            e2.getBooks().add(book2);
            e2.getBooks().add(book3);
            editorialRepository.save(e2);

            //books Join with Editorial
            book1.getEditorials().add(e1);
            bookRepository.save(book1);

            book2.getEditorials().add(e1);
            book2.getEditorials().add(e2);
            bookRepository.save(book2);

            book3.getEditorials().add(e2);
            bookRepository.save(book3);

            book4.getEditorials().add(e1);
            bookRepository.save(book4);
        }

        public void createClients(){
            Client c1 = new Client("001","0954248795","Cristy", "Valenzuela", 21);
            Client c2 = new Client("002","0920232816","Rosy", "Chachi", 22);

            clientRepository.save(c1);
            clientRepository.save(c2);
        }

        public void createWorkers(){
            Worker w1 = new Worker("abc", "0954786123","Juan", "Ponguillo", 35);
            Worker w2 = new Worker("def", "0324879513","Pepe", "Loiza", 20);
            Worker w3 = new Worker("ghi", "0954786321","Helena", "Done", 23);
        workerRepository.save(w1);
        workerRepository.save(w2);
        workerRepository.save(w3);
    }
    public void createLoan(){
        Optional<Client> clientOptional = clientRepository.findById(4l);
        Client client = new Client();
        if(clientOptional.isPresent()){
            client = clientOptional.get();
        }

        Optional<Worker> workerOptional = workerRepository.findById(8l);
        Worker worker = new Worker();
        if(workerOptional.isPresent()){
            worker = workerOptional.get();
        }

        Loan loan = new Loan();
        loan.setSerial("l-0001");
        loan.setLoanDate(new Date());
        loan.setClient(client);
        loan.setDays(30);

        loan.setWorker(worker);
        loanRepository.save(loan);

//        client.getClientLoans().add(loan);
//        clientRepository.save(client);
//        worker.getWorkerLoans().add(loan);
//        workerRepository.save(worker);

        Optional<Book> bookOptional = bookRepository.findById(2l);
        Book b1 = new Book();
        if (bookOptional.isPresent()){
            b1 = bookOptional.get();
        }

        bookOptional = bookRepository.findById(1l);
        Book b2 = new Book();
        if (bookOptional.isPresent()){
            b2 = bookOptional.get();
        }

        LoanDetail l1 = new LoanDetail();
        l1.setLoan(loan);
        l1.setBook(b1);
        loanDetailRepository.save(l1);

        LoanDetail l2 = new LoanDetail();
        l2.setLoan(loan);
        l2.setBook(b2);
        loanDetailRepository.save(l2);

        loan.getDetailsList().add(l1);
        loan.getDetailsList().add(l2);

        loanRepository.save(loan);

//        b1.getLoanDetails().add(l1);
//        bookRepository.save(b1);
//        b2.getLoanDetails().add((l2));
//        bookRepository.save(b2);

    }



@Override
    public void run(String... args) throws Exception {
        createPeople();
        createAnimals();
        createStudents();
        createBooksAuthorsAndEditorials();
        createClients();
        createWorkers();
        createLoan();

            System.out.println("--------- Started BootstrapData ---------");
            System.out.println("Number of People: " +personRepository.count());
            System.out.println("Number of Animals: " +animalRepository.count());
            System.out.println("Number of Students: " +studentRepository.count());
            System.out.println("Number of Clients: " +clientRepository.count());
            System.out.println("Number of Workers: " +workerRepository.count());
            System.out.println("Number of Editorials: " +editorialRepository.count());
            System.out.println("Number of Loan: " +loanRepository.count());
            System.out.println("Number of Loan_Details: " +loanDetailRepository.count());
    }
}
