package com.blog.taxeservice.repository;

import com.blog.taxeservice.model.TaxeTnb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeTnbRepository extends JpaRepository<TaxeTnb,Long> {
}
