package com.codegym.repository;

import com.codegym.model.Material;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MaterialRepository extends PagingAndSortingRepository<Material , Long> {
}
