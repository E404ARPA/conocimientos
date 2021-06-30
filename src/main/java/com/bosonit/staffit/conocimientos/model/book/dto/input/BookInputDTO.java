package com.bosonit.staffit.conocimientos.model.book.dto.input;

import com.bosonit.staffit.conocimientos.model.book.Book;
import com.bosonit.staffit.conocimientos.model.book.dto.output.BookOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookInputDTO {
    private String title;
    private Integer chapters;
    private Double price;

    public BookInputDTO(Book book) {
        this.setTitle(book.getTitle());
        this.setPrice(book.getPrice());
        this.setChapters(book.getChapters());
    }

    public Book Book(BookInputDTO bookInputDTO) {
        Book book = new Book();
        book.setChapters(bookInputDTO.getChapters());
        book.setPrice(bookInputDTO.getPrice());
        book.setTitle(bookInputDTO.getTitle());
        return book;
    }
}
