package com.bosonit.staffit.conocimientos.model.book.dto.output;

import com.bosonit.staffit.conocimientos.model.book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
@NoArgsConstructor
public class BookOutputDTO {
    private String book_id;
    private String title;
    private Integer chapters;
    private Double price;

    public BookOutputDTO(Book book) {
        this.setBook_id(book.getBookId());
        this.setTitle(book.getTitle());
        this.setPrice(book.getPrice());
        this.setChapters(book.getChapters());
    }

    public Book Book(BookOutputDTO bookOutputDTO) {
        Book book = new Book();
        book.setBookId(bookOutputDTO.getBook_id());
        book.setChapters(bookOutputDTO.getChapters());
        book.setPrice(bookOutputDTO.getPrice());
        book.setTitle(bookOutputDTO.getTitle());
        return book;
    }
}
