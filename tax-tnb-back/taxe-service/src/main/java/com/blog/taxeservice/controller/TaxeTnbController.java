package com.blog.taxeservice.controller;

import com.blog.taxeservice.model.TaxeTnb;
import com.blog.taxeservice.service.TaxeTnbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/main/taxeTnb")
@RequiredArgsConstructor
public class TaxeTnbController {

    private final TaxeTnbService taxetnbService;

    @PostMapping("/save")
    public <S extends TaxeTnb> S save(@RequestBody S entity) {
        return taxetnbService.save(entity);
    }

    @GetMapping("/findAll")
    public List<TaxeTnb> findAll() {
        return taxetnbService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<TaxeTnb> findById(@PathVariable Long id) {
        return taxetnbService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        taxetnbService.deleteById(id);
    }
}
