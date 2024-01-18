package com.blog.taxeservice.controller;

import com.blog.taxeservice.model.Taux;
import com.blog.taxeservice.service.TauxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "/api/main/taux")
@RequiredArgsConstructor
public class TauxController {

    private final TauxService tauxService;

    @PostMapping("/save")
    public <S extends Taux> S save(@RequestBody S entity) {
        return tauxService.save(entity);
    }

    @GetMapping("/findAll")
    public List<Taux> findAll() {
        return tauxService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Taux> findById(@PathVariable Long id) {
        return tauxService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        tauxService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Taux> update(@PathVariable Long id, @RequestBody Taux taux){
        return tauxService.updateTaux(id, taux);
    }
}
