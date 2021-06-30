package com.bosonit.staffit.conocimientos.persistence.repository;

import com.bosonit.staffit.conocimientos.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
