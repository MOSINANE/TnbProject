package com.blog.taxeservice.service;

import com.blog.taxeservice.model.Categorie;
import com.blog.taxeservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categorieRepository;

    public <S extends Categorie> S save(S entity) {
        return categorieRepository.save(entity);
    }

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }

    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }

    public ResponseEntity<Categorie> updateCategorie(Long id, Categorie categorie) {
        Optional<Categorie> optionalCategorie = categorieRepository.findById(id);

        if (optionalCategorie.isPresent()) {
            Categorie existingCategorie = optionalCategorie.get();
            existingCategorie.setType(categorie.getType());

            Categorie updatedCategorie = categorieRepository.save(existingCategorie);
            return ResponseEntity.ok(updatedCategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
