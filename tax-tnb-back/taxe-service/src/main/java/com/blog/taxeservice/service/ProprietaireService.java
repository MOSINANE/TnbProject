package com.blog.taxeservice.service;


import com.blog.taxeservice.model.Proprietaire;
import com.blog.taxeservice.repository.ProprietaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProprietaireService {

   private final ProprietaireRepository proprietaireRepository;

    public <S extends Proprietaire> S save(S entity) {
        return proprietaireRepository.save(entity);
    }

    public List<Proprietaire> findAll(Sort sort) {
        return proprietaireRepository.findAll(sort);
    }

    public Optional<Proprietaire> findById(Long id) {
        return proprietaireRepository.findById(id);
    }

    public void deleteById(Long id) {
        proprietaireRepository.deleteById(id);
    }

    public Proprietaire findByCIN(String CIN) {
        return proprietaireRepository.findByCin(CIN);
    }

    public ResponseEntity<Proprietaire> updateProprietaire(Long id, Proprietaire proprietaire) {
        Optional<Proprietaire> optionalProprietaire = proprietaireRepository.findById(id);

        if (optionalProprietaire.isPresent()) {
            Proprietaire existingProprietaire = optionalProprietaire.get();
            existingProprietaire.setNom(proprietaire.getNom());
            existingProprietaire.setPrenom(proprietaire.getPrenom());
            existingProprietaire.setCin(proprietaire.getCin());

            Proprietaire updateProprietaire = proprietaireRepository.save(existingProprietaire);
            return ResponseEntity.ok(updateProprietaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}