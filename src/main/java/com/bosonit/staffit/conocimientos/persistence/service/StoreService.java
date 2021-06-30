package com.bosonit.staffit.conocimientos.persistence.service;

import com.bosonit.staffit.conocimientos.model.book.Book;
import com.bosonit.staffit.conocimientos.model.book.dto.input.BookInputDTO;
import com.bosonit.staffit.conocimientos.model.book.dto.output.BookOutputDTO;
import com.bosonit.staffit.conocimientos.model.store.Store;
import com.bosonit.staffit.conocimientos.persistence.repository.BookRepository;
import com.bosonit.staffit.conocimientos.persistence.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService extends BaseService<Store, String, StoreRepository> {
  @Autowired private BookRepository bookRepository;

  public Store create(Store store) {
    this.repo.save(store);
    return store;
  }

  public Page<?> search(Pageable pageable) {
    return this.repo.findAll(pageable);
  }

  public Store findByStoreId(String id) {
    return this.repo.findById(id).orElse(new Store());
  }

  public Store updateStore(Store store, String id) {
    Optional<Store> retrievedStore = this.repo.findById(id);
    if (retrievedStore.isPresent()) {
      store.setStoreId(id);

      this.repo.deleteById(id);
      this.repo.save(store);

      return store;
    }
    return new Store();
  }

  public String deletestore(String storeId) {
    this.repo.deleteById(storeId);
    return "If store existed, no longer does";
  }

  public Page<?> retrieveBookList(String storeId, Pageable pageable) {
    Optional<Store> storeOp = this.repo.findById(storeId);

    List<Book> stock;
    Page<Book> page = null;
    if (storeOp.isPresent()) {
      stock = new ArrayList<>(storeOp.get().getStock());
      page =  new PageImpl<Book>(stock, pageable, pageable.getPageSize());
    }
    return page;
  }
}
