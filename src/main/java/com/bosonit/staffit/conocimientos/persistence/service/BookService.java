package com.bosonit.staffit.conocimientos.persistence.service;

import com.bosonit.staffit.conocimientos.model.book.Book;
import com.bosonit.staffit.conocimientos.model.book.dto.input.BookInputDTO;
import com.bosonit.staffit.conocimientos.model.book.dto.output.BookOutputDTO;
import com.bosonit.staffit.conocimientos.model.store.Store;
import com.bosonit.staffit.conocimientos.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BookService extends BaseService<Book, String, BookRepository> {
    @Autowired
    private StoreService storeService;

    public BookOutputDTO create(BookInputDTO book) {
        this.repo.save(book.Book(book));
        return new BookOutputDTO(book.Book(book));
    }

    public Page<?> search(Pageable pageable) {
        return this.repo.findAll(pageable);
    }

    public BookOutputDTO findByBookId(String id) {
        return new BookOutputDTO(this.repo.findById(id).orElse(new Book()));
    }

    public BookOutputDTO updateBook(BookInputDTO book, String id) {
        Optional<Book> retievedBook = this.repo.findById(id);
        if (retievedBook.isPresent()) {
            Book updatedBook = book.Book(book);
            updatedBook.setBookId(id);

            this.repo.deleteById(id);
            this.repo.save(updatedBook);

            return new BookOutputDTO(updatedBook);
        }
        return new BookOutputDTO();
    }

    public String deleteBook(String bookId) {
        this.repo.deleteById(bookId);
        return "If book existed, no longer does";
    }

    public void linkToStore(String bookId, String storeId) {
        Optional<Book> bookOp = this.repo.findById(bookId);
        Optional<Store> storeOp = storeService.repo.findById(storeId);

        if (bookOp.isPresent() && storeOp.isPresent()){
            bookOp.get().setStore(storeOp.get());
            this.repo.deleteById(bookId);
            this.repo.save(bookOp.get());
        }

        // book || store doesnt exist
    }

    public void unlinkFromStore(String bookId, String storeId) {
        Optional<Book> bookOp = this.repo.findById(bookId);
        Optional<Store> storeOp = storeService.repo.findById(storeId);

        if (bookOp.isPresent() && storeOp.isPresent()){
            if (bookOp.get().getStore().equals(storeOp.get())){
                bookOp.get().setStore(null);
                this.repo.deleteById(bookId);
                this.repo.save(bookOp.get());
            }
        }

        // book || store doesnt exist || they arent linked
    }
}
