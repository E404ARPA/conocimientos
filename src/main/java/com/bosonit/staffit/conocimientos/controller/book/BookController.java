package com.bosonit.staffit.conocimientos.controller.book;

import com.bosonit.staffit.conocimientos.model.book.Book;
import com.bosonit.staffit.conocimientos.model.book.dto.input.BookInputDTO;
import com.bosonit.staffit.conocimientos.model.book.dto.output.BookOutputDTO;
import com.bosonit.staffit.conocimientos.persistence.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book/")
public class BookController {
  @Autowired BookService bookService;

  @ApiOperation(value = "Creates a book")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = BookOutputDTO.class)})
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody BookInputDTO book) {
    return ResponseEntity.status(200).body(bookService.create(book));
  }

  @ApiOperation(value = "Gets all books")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Page.class)})
  @GetMapping("/search")
  public ResponseEntity<?> search(@PageableDefault(size = 10, page = 0) Pageable pageable) {
    return ResponseEntity.status(200).body(bookService.search(pageable));
  }

  @ApiOperation(value = "Gets a book by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "", response = BookOutputDTO.class),
    @ApiResponse(code = 404, message = "")
  })
  @GetMapping("/findById/{bookId}")
  public ResponseEntity<?> findById(@PathVariable String bookId) {
    BookOutputDTO response = bookService.findByBookId(bookId);
    return !response.getBook_id().isEmpty()
        ? ResponseEntity.status(200).body(response)
        : ResponseEntity.status(404).body("No book found with the given Id");
  }

  @ApiOperation(value = "Updates a book info")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Page.class)})
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@RequestParam BookInputDTO book, @PathVariable String id) {
    BookOutputDTO response = bookService.updateBook(book, id);
    return !response.getBook_id().isEmpty()
            ? ResponseEntity.status(200).body(response)
            : ResponseEntity.status(404).body("No book found with the given Id");
  }

  @ApiOperation(value = "links a book to an store")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  @PutMapping("/update/{bookId}/linkToStore/{storeId}")
  public ResponseEntity<?> linkToStore(@RequestParam String bookId, @PathVariable String storeId) {
    bookService.linkToStore(bookId, storeId);
    return ResponseEntity.status(200).body("Elements linked");
  }

  @ApiOperation(value = "unlinks a book from a store")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  @PutMapping("/update/{bookId}/linkToStore/{storeId}")
  public ResponseEntity<?> unlinkFromStore(@RequestParam String bookId, @PathVariable String storeId) {
    bookService.unlinkFromStore(bookId, storeId);
    return ResponseEntity.status(200).body("Elements unlinked");
  }

  @ApiOperation(value = "Deletes a book by id")
  @ApiResponses({
          @ApiResponse(code = 200, message = "", response = BookOutputDTO.class),
          @ApiResponse(code = 404, message = "")
  })
  @DeleteMapping("/delete/{bookId}")
  public ResponseEntity<?> delete(@PathVariable String bookId) {
    return ResponseEntity.status(200).body(bookService.deleteBook(bookId));
  }
}
