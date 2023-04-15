package io.mars.cereal.service;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import io.mars.cereal.data.category.CategoryRepository;
import io.mars.cereal.data.company.CompanyRepository;
import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.data.shop.ShopRepository;
import io.mars.cereal.data.shopitem.ShopItemRepository;
import io.mars.cereal.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitialDataSeedService implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ShopItemRepository shopItemRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final Faker faker = new Faker();
    private final RandomService rand = faker.random();

    @Override
    public void run(String... args) throws Exception {
        saveCategories();
        saveCompanies();
        saveShops();

        saveProducts();
        saveShopItems();
        System.out.println("do whatever it takes");
    }

    private void saveCategories() {

        List<Category> mainCategories = new ArrayList<>();
        List<Category> firstSubCategories = new ArrayList<>();
        List<Category> secondSubCategories = new ArrayList<>();

        IntStream.range(0, 10).forEach(n -> {
            Category category = new Category(faker.name().title());
            mainCategories.add(category);
        });

        IntStream.range(0, 73).forEach(n -> {
            Category subCategory = new Category(faker.name().title(),
                    Set.of(mainCategories.get(rand.nextInt(0, 9)),
                            mainCategories.get(rand.nextInt(0, 9))));
            firstSubCategories.add(subCategory);
        });

        IntStream.range(0, 500).forEach(n -> {
            Category subCategory = new Category(faker.name().title(),
                    Set.of(firstSubCategories.get(rand.nextInt(0, 72)),
                            firstSubCategories.get(rand.nextInt(0, 72)),
                            firstSubCategories.get(rand.nextInt(0, 72))));
            secondSubCategories.add(subCategory);
        });

        categoryRepository.saveAll(mainCategories);
        categoryRepository.saveAll(firstSubCategories);
        categoryRepository.saveAll(secondSubCategories);
    }


    private void saveShops() {

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Shop> shops = new ArrayList<>();

        IntStream.range(0, 93).forEach(n -> {
            Shop shop = new Shop(faker.company().name(), faker.friends().quote());
            Set<Category> categoryList = new HashSet<>();
            categoryList.add(mainCategories.get(rand.nextInt(9)));
            categoryList.add(categories.get(rand.nextInt(0, 500)));
            shop.setCategories(categoryList);
            shops.add(shop);
        });

        shopRepository.saveAll(shops);
    }

    private void saveCompanies() {

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Company> companies = (List<Company>) companyRepository.findAll();

        IntStream.range(0, 37).forEach(n -> {
            Company company = new Company(faker.company().name());
            Set<Category> categoryList = new HashSet<>();
            categoryList.add(mainCategories.get(rand.nextInt(9)));
            categoryList.add(categories.get(rand.nextInt(0, 490)));
            company.setCategories(categoryList);
            companies.add(company);
        });

        companyRepository.saveAll(companies);
    }


    private void saveProducts() {

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Company> companies = (List<Company>) companyRepository.findAll();

        List<Product> products = new ArrayList<>();

        IntStream.range(0, 1372).forEach(n -> {
            Product product = new Product(faker.company().name(),
                    companies.get(rand.nextInt(0, 36)), getDetails(rand.nextInt(3, 7)));
            Set<Category> categoryList = new HashSet<>();
            Category main = mainCategories.get(rand.nextInt(9));
            Category sub = categories.get(rand.nextInt(0, 500));
            categoryList.add(main);
            categoryList.add(sub);
            product.setCategories(categoryList);
            products.add(product);
        });

        productRepository.saveAll(products);
    }

    private void saveShopItems() {

        List<Shop> shopList = (List<Shop>) shopRepository.findAll();
        List<Product> productList = (List<Product>) productRepository.findAll();

        List<ShopItem> items = new ArrayList<>();

        productList.forEach(product -> {
            IntStream.range(2, rand.nextInt(9)).forEach(number -> {
                ShopItem item = new ShopItem(shopList.get(rand.nextInt(0, 90)),
                        product, rand.nextDouble() * 100, rand.nextLong(100));
                items.add(item);
            });
        });
        shopItemRepository.saveAll(items);
    }

    private List<Detail> getDetails(int amount) {

        List<Detail> details = new ArrayList<>();
        IntStream.range(0, amount).forEach(a -> {
            details.add(Detail.of(faker.stock().nsdqSymbol(), faker.stock().nyseSymbol()));
        });
        return details;
    }

    private void testSavedData(){
    }
}
