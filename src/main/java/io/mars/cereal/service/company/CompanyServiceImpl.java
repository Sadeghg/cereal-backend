package io.mars.cereal.service.company;

import io.mars.cereal.data.company.CompanyRepository;
import io.mars.cereal.model.Company;
import io.mars.cereal.model.exception.ContentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository repository;

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public Iterable<Company> saveAll(Iterable<Company> companies) {
        return repository.saveAll(companies);
    }

    @Override
    public Company find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("no such company found"));
    }

    @Override
    public Iterable<Company> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
