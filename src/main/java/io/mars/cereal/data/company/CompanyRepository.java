package io.mars.cereal.data.company;


import io.mars.cereal.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
