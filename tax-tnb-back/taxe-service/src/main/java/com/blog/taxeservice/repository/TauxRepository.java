package com.blog.taxeservice.repository;

import com.blog.taxeservice.model.Taux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TauxRepository extends JpaRepository<Taux,Long> {
}
