package io.mars.cereal;

import io.mars.cereal.data.company.CompanyRepository;
import io.mars.cereal.model.Company;
import io.mars.cereal.service.company.CompanyServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private final Company sony = new Company(50L,"SONY");
    private final Company lg = new Company(60L, "LG");
    private final Company apple = new Company(70L, "Apple");

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyServiceImpl service;

    @Test
    public void saveCompany(){
        //given

        //when
        when(repository.save(apple)).thenReturn(apple);

        //then
        Company result = service.save(apple);
        assertNotNull(result.getId());
        verify(repository).save(apple);
    }

    @Test
    public void saveAllCompanies(){
        //given

        //when
        when(repository.saveAll(anyIterable())).thenReturn(List.of(lg, apple));

        //then
        List<Company> companies = (List<Company>) service.saveAll(List.of(lg, apple));
        assertEquals(companies.size(), 2);
        verify(repository).saveAll(List.of(lg, apple));
    }

    @Test
    public void findCompanyById(){
        //given

        //when
        when(repository.findById(anyLong())).thenReturn(Optional.of(lg));

        //then
        Company result = service.find(60L);
        assertNotNull(result.getId());
        verify(repository).findById(60L);
    }

    @Test
    public void findAllCompaniesById(){
        //given

        //when
        when(repository.findAllById(anyIterable())).thenReturn(List.of(lg, apple));

        //then
        List<Company> companies = (List<Company>) service.findAllById(List.of(50L, 60L));
        assertEquals(companies.size(), 2);
        verify(repository).findAllById(List.of(50L, 60L));
    }

    @Test
    public void delete(){
        //given

        //when

        //then
        service.delete(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    public void deleteAllCompaniesById(){
        //given

        //when

        //then
        service.deleteAllById(anyIterable());
        verify(repository).deleteAllById(anyIterable());
    }



}
