package com.bosonit.staffit.conocimientos.controller.store;

import com.bosonit.staffit.conocimientos.model.book.dto.input.BookInputDTO;
import com.bosonit.staffit.conocimientos.model.book.dto.output.BookOutputDTO;
import com.bosonit.staffit.conocimientos.model.store.Store;
import com.bosonit.staffit.conocimientos.persistence.service.BookService;
import com.bosonit.staffit.conocimientos.persistence.service.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class StoreController {
  @Autowired StoreService storeService;

  @ApiOperation(value = "Creates a store")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Store.class)})
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Store store) {
    return ResponseEntity.status(200).body(storeService.create(store));
  }

  @ApiOperation(value = "Gets all stores")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Page.class)})
  @GetMapping("/search")
  public ResponseEntity<?> search(@PageableDefault(size = 10, page = 0) Pageable pageable) {
    return ResponseEntity.status(200).body(storeService.search(pageable));
  }

  @ApiOperation(value = "Gets a store by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "", response = Store.class),
    @ApiResponse(code = 404, message = "")
  })
  @GetMapping("/findById/{storeId}")
  public ResponseEntity<?> findById(@PathVariable String storeId) {
    Store response = storeService.findByStoreId(storeId);
    return !response.getStoreId().isEmpty()
        ? ResponseEntity.status(200).body(response)
        : ResponseEntity.status(404).body("No store found with the given Id");
  }

  @ApiOperation(value = "Updates a store info")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Page.class)})
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@RequestParam Store store, @PathVariable String id) {
    Store response = storeService.updateStore(store, id);
    return !response.getStoreId().isEmpty()
        ? ResponseEntity.status(200).body(response)
        : ResponseEntity.status(404).body("No store found with the given Id");
  }

  @ApiOperation(value = "Deletes a store by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "", response = BookOutputDTO.class),
    @ApiResponse(code = 404, message = "")
  })
  @DeleteMapping("/delete/{bookId}")
  public ResponseEntity<?> delete(@PathVariable String storeId) {
    return ResponseEntity.status(200).body(storeService.deletestore(storeId));
  }

  @ApiOperation(value = "Gets all books from an store")
  @ApiResponses({@ApiResponse(code = 200, message = "", response = Page.class)})
  @GetMapping("/books/{idStore}")
  public ResponseEntity<?> retrieveBookList(
      @PathVariable String storeId, @PageableDefault(size = 10, page = 0) Pageable pageable) {
    return ResponseEntity.status(200).body(storeService.retrieveBookList(storeId, pageable));
  }
}
