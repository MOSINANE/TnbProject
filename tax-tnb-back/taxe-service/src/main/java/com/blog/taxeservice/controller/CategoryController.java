package com.blog.taxeservice.controller;

import com.blog.taxeservice.model.Categorie;
import com.blog.taxeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/main/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categorieService;

    @PostMapping("/save")
    public <S extends Categorie> S save(@RequestBody S entity) {
        return categorieService.save(entity);
    }

    @GetMapping("/findAll")
    public List<Categorie> findAll() {
        return categorieService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Categorie> findById(@PathVariable Long id) {
        return categorieService.findById(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        categorieService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Categorie> update(@PathVariable Long id, @RequestBody Categorie categorie){
        return categorieService.updateCategorie(id, categorie);
    }
}
