package io.mars.cereal;

import io.mars.cereal.data.company.CompanyRepository;
import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.model.Company;
import io.mars.cereal.model.Details;
import io.mars.cereal.model.Product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setRepository() {
        Company lg = new Company("LG");
        Company sony = new Company("SONY");
        Company apple = new Company("Apple");

        List<Details> tvDetails =
                Details.of("weight", "2 kg", "original", "yes", "power", "130");
        List<Details> playstationDetails =
                Details.of("weight", "4 kg", "original", "yes", "power", "330", "color", "glacier white");
        List<Details> iphoneDetails =
                Details.of("weight", "200 grams", "original", "yes", "color", "rose gold");

        Product oledTv = new Product("OLED TV", lg, tvDetails);
        Product playStation = new Product("PS5", sony, playstationDetails);
        Product iphone = new Product("Iphone 12 PRO MAX", apple, iphoneDetails);

        companyRepository.saveAll(List.of(sony, lg, apple));
        productRepository.saveAll(List.of(oledTv, playStation, iphone));
    }

    @Test
    @Order(1)
    public void saveProduct() {
        //given
        List<Details> tvDetails =
                Details.of("refresh rate", "120 Hz", "screen size", "27 Inch", "resolution", "1920*1080");
        Company lg = new Company(40L, "LG");
        Product gamingTV = new Product("ROG TV", lg, tvDetails);

        //when
        Product saveResult = productRepository.save(gamingTV);

        //then
        assertNotNull(saveResult.getId());
        assertEquals(saveResult.getName(), gamingTV.getName());
        assertEquals(saveResult.getDetails().size(), tvDetails.size());
    }

    @Test
    @Order(2)
    public void findById() {
        //given
        Long productId = 15L;
        Integer detailsSize = 3;
        String productName = "OLED TV";

        //when
        Product result = productRepository.findById(productId)
                .orElseGet(Product::new);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), productName);
        assertEquals(result.getDetails().size(), detailsSize);
    }

    @Test
    @Order(3)
    public void findByIdNotFound() {
        Optional<Product> result = productRepository.findById(10L);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(4)
    public void updateProduct() {
        //given
        Long productId = 12L;
        Product product = productRepository.findById(productId)
                .orElseGet(Product::new);
        String previousName = product.getName();

        //when
        product.setName("Gaming Tv");
        Product updateProduct = productRepository.save(product);

        //then
        assertEquals(updateProduct.getId(), product.getId());
        assertNotEquals(updateProduct.getName(), previousName);
    }

    @Test
    @Order(5)
    void deleteById() {
        //given
        Long productId = 12L;

        //when
        productRepository.deleteById(productId);
        Optional<Product> result = productRepository.findById(productId);

        //then
        assertTrue(result.isEmpty());
    }
}
