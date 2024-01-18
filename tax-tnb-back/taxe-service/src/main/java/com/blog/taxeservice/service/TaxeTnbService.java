package com.blog.taxeservice.service;

import com.blog.taxeservice.model.TaxeTnb;
import com.blog.taxeservice.repository.TaxeTnbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxeTnbService {
    private final TaxeTnbRepository taxetnbRepository;

    public <S extends TaxeTnb> S save(S entity) {
        return taxetnbRepository.save(entity);
    }

    public List<TaxeTnb> findAll() {
        return taxetnbRepository.findAll();
    }

    public Optional<TaxeTnb> findById(Long id) {
        return taxetnbRepository.findById(id);
    }

    public void deleteById(Long id) {
        taxetnbRepository.deleteById(id);
    }
}
