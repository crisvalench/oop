package com.ups.oop.repository;
import com.ups.oop.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository  extends CrudRepository<Book, Long> {
}
