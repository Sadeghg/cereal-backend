package io.mars.cereal.controller;

import io.mars.cereal.model.Company;
import io.mars.cereal.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/")
public class CompanyController {

    private final CompanyService service;

    @GetMapping("{id}")
    public ResponseEntity<Company> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("save")
    public ResponseEntity<Company> save(@RequestBody Company company) {
        return ResponseEntity.ok(service.save(company));
    }

    @PutMapping("update")
    public ResponseEntity<Company> update(@RequestBody Company company) {
        return ResponseEntity.ok(service.save(company));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

