package com.blog.taxeservice.repository;

import com.blog.taxeservice.model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire,Long> {
    Proprietaire findByCin(String CIN);
    Boolean existsByCin(String CIN);
}
