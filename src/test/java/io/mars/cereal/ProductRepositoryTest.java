package io.mars.cereal;

import com.mysql.cj.conf.LongProperty;
import io.mars.cereal.data.company.CompanyRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    private List<Product> products;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setRepository(){
        Company lg = new Company("LG");
        Company sony = new Company( "SONY");
        Company apple = new Company( "Apple");

        List<Detail> tvDetails =
               Detail.of("weight", "2 kg", "original", "yes", "power", "130");
        List<Detail> playstationDetails =
               Detail.of("weight", "4 kg", "original", "yes", "power", "330", "color", "glacier white");
        List<Detail> iphoneDetails =
               Detail.of("weight", "200 grams", "original", "yes", "color", "rose gold");

        Product oledTv = new Product("OLED TV", lg, tvDetails, null);
        Product playStation = new Product("PS5", sony, playstationDetails, null);
        Product iphone = new Product("Iphone 12 PRO MAX", apple, iphoneDetails, null);

        companyRepository.saveAll(List.of(lg, sony, apple));
        productRepository.saveAll(List.of(oledTv, iphone, playStation));
    }

    @Test
    public void saveProduct(){
        //given
        Company lg = new Company(40L,"LG");
        List<Detail> tvDetails =
                Detail.of("screen size", "27 Inch", "Refresh rate", "120 Hz", "Resolution", "1920*1080");
        Product product = new Product("Gamin TV", lg, tvDetails);
        String productName = product.getName();

        //when
        Product saveResult = productRepository.save(product);

        //then
        assertNotNull(saveResult.getId());
        assertEquals(saveResult.getName(),productName);
    }

    @Test
    public void findById(){
        //given
        Long productId = 4L; //1 for singleton test
        String productName = "OLED TV";
        Integer detailsSize = 3;

        //when
        Product result = productRepository.findById(productId)
                .orElseGet(Product::new);

        //then
        assertEquals(result.getDetails().size() , detailsSize);
        assertEquals(result.getName(), productName);
    }

    @Test
    public void updateProduct(){
        //given
        Long productId = 1L;
        Product product = productRepository.findById(productId)
                .orElseGet(Product::new);
        String previousName = product.getName();

        //when
        product.setName("Gaming TV");
        Product updateProduct = productRepository.save(product);

        //then
        assertEquals(updateProduct.getId(), productId);
        assertNotEquals(updateProduct.getName(), previousName);
    }

    @Test
    void deleteById(){
        //given
        Long productId = 12L;

        //when
        productRepository.deleteById(productId);
        Optional<Product> result = productRepository.findById(productId);

        //then
        assertTrue(result.isEmpty());
    }
}
