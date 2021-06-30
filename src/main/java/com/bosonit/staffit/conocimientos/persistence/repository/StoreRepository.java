package com.bosonit.staffit.conocimientos.persistence.repository;

import com.bosonit.staffit.conocimientos.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
}
