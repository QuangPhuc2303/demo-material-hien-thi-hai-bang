package com.codegym.service;

import com.codegym.model.Material;

public interface MaterialService {

    Iterable<Material> findAll();

    Material findById(Long id);

    void save(Material material);

    void remove(Long id);
}
