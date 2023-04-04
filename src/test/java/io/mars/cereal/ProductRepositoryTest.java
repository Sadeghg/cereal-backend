package io.mars.cereal;

import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.model.Company;
import io.mars.cereal.model.Detail;
import io.mars.cereal.model.Product;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    private List<Product> products;
    @Mock
    private ProductRepository repository;

    @BeforeEach
    public void setRepository(){
        Company lg = new Company(40L,"LG");
        Company sony = new Company(50L, "SONY");
        Company apple = new Company(60L, "Apple");

        List<Detail> tvDetails =
               Detail.of("weight", "2 kg", "original", "yes", "power", "130");
        List<Detail> playstationDetails =
               Detail.of("weight", "4 kg", "original", "yes", "power", "330", "color", "glacier white");
        List<Detail> iphoneDetails =
               Detail.of("weight", "200 grams", "original", "yes", "color", "rose gold");

        Product oledTv = new Product(10L, "OLED TV", lg, tvDetails);
        Product playStation = new Product(20L, "PS5", sony, playstationDetails);
        Product iphone = new Product(30L, "Iphone 12 PRO MAX", apple, iphoneDetails);

        products = List.of(oledTv, playStation, iphone);
    }

    @Test
    public void saveProduct(){
        //given
        Product product = new Product("TV");

        //when
        when(repository.save(any(Product.class))).thenReturn(product);

        //then
        Product saveResult = repository.save(product);
        assertNull(saveResult.getId());
        assertEquals(saveResult.getName(), product.getName());
    }

    @Test
    public void findById(){
        //given
        Product product = products.get(0);

        //when
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));

        //then
        Optional<Product> result = repository.findById(anyLong());
        assertTrue(result.isPresent());
        assertEquals(result.get().getDetails().size() , 3);
        assertEquals(result.get().getName(), product.getName());
    }

    @Test
    public void updateProduct(){
        //given
        Product product = products.get(0);
        Company sony = new Company(50L , "SONY");

        //when
        Product updateProduct = new Product(product.getId(), product.getName(), sony, product.getDetails());
        when(repository.save(any(Product.class))).thenReturn(updateProduct);

        //then
        Product result = repository.save(updateProduct);
        assertEquals(result.getId(), product.getId());
        assertNotEquals(result.getCompany().getId(), product.getCompany().getId());
    }

    @Test
    void deleteById(){
        //given

        //when

        //then
        repository.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }
}
