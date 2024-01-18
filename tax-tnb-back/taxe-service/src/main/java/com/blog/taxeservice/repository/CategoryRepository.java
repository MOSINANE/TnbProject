package com.blog.taxeservice.repository;

import com.blog.taxeservice.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie,Long> {

}
