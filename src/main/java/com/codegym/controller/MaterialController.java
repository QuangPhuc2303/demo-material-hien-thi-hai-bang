package com.codegym.controller;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import com.codegym.service.MaterialService;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("suppliers")
    public Iterable<Supplier> suppliers() {
        return supplierService.findAll();
    }

    @GetMapping("/materials")
    public ModelAndView listMaterial(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Material> materials;
        if (s.isPresent()) {
            materials = materialService.findAllByNameContaining(s.get(), pageable);
        } else {
            materials = materialService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/material/list");
        modelAndView.addObject("materials", materials);
        return modelAndView;
    }

    @GetMapping("/create-material")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/material/create");
        modelAndView.addObject("material", new Material());
        return modelAndView;
    }

    @PostMapping("/create-material")
    public ModelAndView createMaterial(@ModelAttribute("material") Material material) {
        materialService.save(material);
        ModelAndView modelAndView = new ModelAndView("/material/create");
        modelAndView.addObject("material", new Material());
        modelAndView.addObject("message", "new material created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-material/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            ModelAndView modelAndView = new ModelAndView("/material/edit");
            modelAndView.addObject("material", material);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-material")
    public ModelAndView editMaterial(@ModelAttribute("material") Material material) {
        materialService.save(material);
        ModelAndView modelAndView = new ModelAndView("/material/edit");
        modelAndView.addObject("material", material);
        modelAndView.addObject("message", "materia update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-material/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            ModelAndView modelAndView = new ModelAndView("/material/delete");
            modelAndView.addObject("material", material);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-material")
    public String deleteMaterial(@ModelAttribute("material") Material material) {
        materialService.remove(material.getId());
        return "redirect:materials";
    }
}
