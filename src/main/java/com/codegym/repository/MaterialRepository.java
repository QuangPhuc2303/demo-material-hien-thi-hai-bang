package com.codegym.repository;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MaterialRepository extends PagingAndSortingRepository<Material, Long> {

    Iterable<Material> findAllBySupplier(Supplier supplier);

    Page<Material> findAllByNameContaining (String name, Pageable pageable);



}
