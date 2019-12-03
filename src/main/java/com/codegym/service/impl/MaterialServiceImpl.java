package com.codegym.service.impl;

import com.codegym.model.Material;
import com.codegym.repository.MaterialRepository;
import com.codegym.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Iterable<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findOne(id);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void remove(Long id) {
        materialRepository.delete(id);
    }
}
