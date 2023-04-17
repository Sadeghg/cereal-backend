package io.mars.cereal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.mars.cereal.controller.ProductController;
import io.mars.cereal.model.Company;
import io.mars.cereal.model.Detail;
import io.mars.cereal.model.Product;
import io.mars.cereal.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
    private MockMvc mockMvc;

    private List<Product> productList;

    @Mock
    public ProductService service;

    @InjectMocks
    public ProductController controller;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Company lg = new Company(40L,"LG");
        Company sony = new Company(50L, "SONY");
        Company apple = new Company(60L, "Apple");

        List<Detail> tvDetails =
               Detail.of("weight", "2 kg", "original", "yes", "power", "130");
        List<Detail> playstationDetails =
               Detail.of("weight", "4 kg", "original", "yes", "power", "330", "color", "glacier white");
        List<Detail> iphoneDetails =
               Detail.of("weight", "200 grams", "original", "yes", "color", "rose gold");

        Product oledTv = new Product(10L, "OLED TV", lg, tvDetails, null);
        Product playStation = new Product(20L, "PS5", sony, playstationDetails, null);
        Product iphone = new Product(30L, "Iphone 12 PRO MAX", apple, iphoneDetails, null);

        productList = List.of(oledTv, playStation, iphone);
    }
    
    @Test
    public void saveProduct() throws Exception {
        //given
        Company sony = new Company(50L, "SONY");
        List<Detail> playstationDetails =
               Detail.of("weight", "4 kg", "power", "330", "color", "glacier white");
        Product playStation = new Product(20L, "PS5", sony, playstationDetails ,null);

        //when
        when(service.save(playStation)).thenReturn(playStation);

        //then
        mockMvc.perform(post("/api/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writer.writeValueAsString(playStation)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("PS5")));
        verify(service).save(playStation);
    }

    @Test
    public void findById() throws Exception {
        //given
        Company sony = new Company(50L, "SONY");
        List<Detail> playstationDetails =
               Detail.of("weight", "3 kg", "power", "270", "color", "black mate");
        Product playStation = new Product(20L, "PS4", sony, playstationDetails, null);

        //when
        when(service.find(20L)).thenReturn(playStation);

        //then
        mockMvc.perform(get("/api/product/{id}", "20")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath(("$"), notNullValue()))
                .andExpect(jsonPath("$.name", is("PS4")));
        verify(service).find(20L);
    }

    @Test
    public void updateProduct() throws Exception {
        //given
        Company lg = new Company(40L,"LG");
        List<Detail> tvDetails =
               Detail.of("weight", "2 kg", "original", "yes", "power", "130");
        Product oledTv = new Product(10L, "OLED TV", lg, tvDetails, null);

        //when
        when(service.save(oledTv)).thenReturn(oledTv);

        //then
        mockMvc.perform(put("/api/product/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(oledTv)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("OLED TV")));
        verify(service).save(oledTv);
    }

    @Test
    public void deleteProduct() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(delete("/api/product/delete/{id}", "20")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        verify(service).delete(20L);
    }
}
