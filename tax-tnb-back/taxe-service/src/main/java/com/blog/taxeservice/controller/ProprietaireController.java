package com.blog.taxeservice.controller;

import com.blog.taxeservice.model.Proprietaire;
import com.blog.taxeservice.service.ProprietaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping (path = "/api/main/proprietaire")
@RequiredArgsConstructor
public class ProprietaireController {

    private final ProprietaireService proprietaireService;

    @PostMapping("/save")
    public <S extends Proprietaire> S save(@RequestBody S entity) {
        return proprietaireService.save(entity);
    }

    @GetMapping("/findAll")
    public List<Proprietaire> findAll(Sort sort) {
        return proprietaireService.findAll(sort);
    }

    @GetMapping("/findById/{id}")
    public Optional<Proprietaire> findById(@PathVariable Long id) {
        return proprietaireService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        proprietaireService.deleteById(id);
    }

    @GetMapping("/findByCin/{CIN}")
    public Proprietaire findByCin(@PathVariable String CIN) {
        return proprietaireService.findByCIN(CIN);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Proprietaire> update(@PathVariable Long id, @RequestBody Proprietaire proprietaire){
        return proprietaireService.updateProprietaire(id, proprietaire);
    }
}
