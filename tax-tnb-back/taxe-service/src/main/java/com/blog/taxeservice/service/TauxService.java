package com.blog.taxeservice.service;

import com.blog.taxeservice.model.Taux;
import com.blog.taxeservice.repository.TauxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TauxService {


    private final TauxRepository tauxRepository;

    public <S extends Taux> S save(S entity) {
        return tauxRepository.save(entity);
    }

    public List<Taux> findAll() {
        return tauxRepository.findAll();
    }

    public Optional<Taux> findById(Long id) {
        return tauxRepository.findById(id);
    }

    public void deleteById(Long id) {
        tauxRepository.deleteById(id);
    }

    public ResponseEntity<Taux> updateTaux(Long id, Taux taux) {
        Optional<Taux> optionalTaux = tauxRepository.findById(id);

        if (optionalTaux.isPresent()) {
            Taux existingTaux = optionalTaux.get();
            existingTaux.setMontant(taux.getMontant());
            existingTaux.setCategorie(taux.getCategorie());

            Taux updatedTaux = tauxRepository.save(existingTaux);
            return ResponseEntity.ok(updatedTaux);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
