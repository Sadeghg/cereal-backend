package io.mars.cereal.service;

import com.github.javafaker.Animal;
import com.github.javafaker.Faker;
import com.github.javafaker.Job;
import com.github.javafaker.service.RandomService;
import io.mars.cereal.data.cart.CartRepository;
import io.mars.cereal.data.cartitem.CartItemRepository;
import io.mars.cereal.data.category.CategoryRepository;
import io.mars.cereal.data.company.CompanyRepository;
import io.mars.cereal.data.invoice.InvoiceRepository;
import io.mars.cereal.data.order.OrderRepository;
import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.data.shop.ShopRepository;
import io.mars.cereal.data.shopitem.ShopItemRepository;
import io.mars.cereal.model.*;
import io.mars.cereal.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
@RequiredArgsConstructor
public class InitialDataSeedService implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ShopItemRepository shopItemRepository;
    private final CartItemRepository cartItemRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final CartRepository cartRepository;

    private final Faker faker = new Faker();
    private final RandomService rand = faker.random();

    @Override
    public void run(String... args) throws Exception {
        saveCategories();
        saveCompanies();
        saveShops();

        saveProducts();
        saveShopItems();

        saveCart();
        saveInvoice();
        saveOrders();
        System.out.println("do whatever it takes");
    }

    private void saveCategories() {

        if (cartRepository.count() != 0) return;

        Job job = faker.job();
        Animal animal = faker.animal();

        List<Category> mainCategories = new ArrayList<>();
        List<Category> firstSubCategories = new ArrayList<>();
        List<Category> secondSubCategories = new ArrayList<>();
        List<Category> thirdSubCategories = new ArrayList<>();
        List<Category> fourthSubCategories = new ArrayList<>();
        List<Category> fifthSubCategories = new ArrayList<>();
        List<Category> sixthSubCategories = new ArrayList<>();

        IntStream.range(0, 10).forEach(value -> {
            Category category = new Category(job.field());
            mainCategories.add(category);
        });

        IntStream.range(0, 51).forEach(value -> {
            Set<Category> children = getCategories(mainCategories, 2, 9);
            Category subCategory = new Category(animal.name(), children);
            firstSubCategories.add(subCategory);
        });

        IntStream.range(0, 91).forEach(value -> {
            Set<Category> children = getCategories(firstSubCategories, 3, 50);
            Category subCategory = new Category(job.title(), children);
            secondSubCategories.add(subCategory);
        });

        IntStream.range(0, 131).forEach(value -> {
            Set<Category> children = getCategories(secondSubCategories, 3, 90);
            Category subCategory = new Category(job.title(), children);
            thirdSubCategories.add(subCategory);
        });

        IntStream.range(0, 201).forEach(value -> {
            Set<Category> children = getCategories(thirdSubCategories, 3, 130);
            Category subCategory = new Category(job.title(), children);
            fourthSubCategories.add(subCategory);
        });

        IntStream.range(0, 291).forEach(value -> {
            Set<Category> children = getCategories(fourthSubCategories, 3, 200);
            Category subCategory = new Category(job.title(), children);
            fifthSubCategories.add(subCategory);
        });

        IntStream.range(0, 421).forEach(value -> {
            Set<Category> children = getCategories(fifthSubCategories, 3, 290);
            Category subCategory = new Category(job.title(), children);
            sixthSubCategories.add(subCategory);
        });

        categoryRepository.saveAll(mainCategories);
        categoryRepository.saveAll(firstSubCategories);
        categoryRepository.saveAll(secondSubCategories);
        categoryRepository.saveAll(thirdSubCategories);
        categoryRepository.saveAll(fourthSubCategories);
        categoryRepository.saveAll(fifthSubCategories);
        categoryRepository.saveAll(sixthSubCategories);
    }


    private void saveShops() {

        if (shopRepository.count() != 0) return;

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Shop> shops = new ArrayList<>();

        IntStream.range(0, 73).forEach(n -> {
            List<Category> categoryList = List.of(mainCategories.get(rand.nextInt(mainCategories.size() -1)),
                    categories.get(rand.nextInt(0, categories.size() -1)));
            shops.add(new Shop(faker.company().name(), faker.friends().quote(), categoryList));
        });

        shopRepository.saveAll(shops);
    }

    private void saveCompanies() {

        if (companyRepository.count() != 0) return;

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Company> companies = (List<Company>) companyRepository.findAll();

        IntStream.range(0, 37).forEach(n -> {
            List<Category> categoryList =List.of(mainCategories.get(rand.nextInt(mainCategories.size() -1)),
                    categories.get(rand.nextInt(0, categories.size() -1)));
            companies.add(new Company(faker.company().name(), categoryList));
        });

        companyRepository.saveAll(companies);
    }


    private void saveProducts() {

        if (productRepository.count() != 0) return;

        List<Category> categories = categoryRepository.findSubCategories();
        List<Category> mainCategories = categoryRepository.findMainCategories();
        List<Company> companies = (List<Company>) companyRepository.findAll();

        List<Product> products = new ArrayList<>();

        IntStream.range(0, 3172).forEach(n -> {
            String productName = faker.commerce().productName();
            Company  company = companies.get(rand.nextInt(0, 36));
            List<Category> categoryList = List.of(mainCategories.get(rand.nextInt(mainCategories.size() -1)),
                    categories.get(rand.nextInt(0, categories.size() -1)));
            products.add(new Product(productName, company,
                    getDetails(rand.nextInt(3, 7)), categoryList));
        });

        productRepository.saveAll(products);
    }

    private void saveShopItems() {

        if (shopItemRepository.count() != 0) return;

        List<Category> categories = categoryRepository.findSubCategories();
        List<Shop> shopList = (List<Shop>) shopRepository.findAll();
        List<Product> productList = (List<Product>) productRepository.findAll();

        List<ShopItem> items = new ArrayList<>();

        productList.forEach(product -> {;
            IntStream.range(0, rand.nextInt(2, 21)).forEach(number -> {
                List<Category> itemCategories = new ArrayList<>();
                IntStream.range(0, rand.nextInt(2, 9)).forEach(value ->
                        itemCategories.add(categories.get(rand.nextInt(categories.size() -1))));
                List<ProductPrice> prices = new ArrayList<>();
                LocalDateTime priceDate = randomDate();
                IntStream.range(3, 9).forEach(value -> {
                    prices.add(new ProductPrice(rand.nextDouble() * 100,
                            priceDate.minusDays(value + rand.nextInt(10, 500))));
                });
                items.add(new ShopItem(shopList.get(rand.nextInt(0, shopList.size() -1)),
                                product, rand.nextLong(100),
                        prices.get(0).getPrice(), prices, itemCategories));
            });
        });
        shopItemRepository.saveAll(items);
    }

    private void saveCart() {

        if (cartRepository.count() != 0) return;

        List<ShopItem> items = (List<ShopItem>) shopItemRepository.findAll();
        List<Cart> carts = new ArrayList<>();
        List<CartItem> cartItems = new ArrayList<>();
        IntStream.range(0, 173).forEach(value -> {
            LocalDateTime purchasedDate = randomDate();
            LocalDateTime addDate = purchasedDate.minusMinutes(rand.nextInt(5, 700));
            carts.add(new Cart(rand.nextInt(1, 10000).doubleValue(), purchasedDate, addDate));
        });

        cartRepository.saveAll(carts);
        List<Cart> savedCarts = (List<Cart>) cartRepository.findAll();

        savedCarts.forEach(cart -> {
            IntStream.range(0, rand.nextInt(2, 9)).forEach(value -> {
                ShopItem shopItem = items.get(rand.nextInt(0, 1700));
                CartItem cartItem = new CartItem(rand.nextInt(1, 7), cart, shopItem);
                cartItems.add(cartItem);
            });
        });
        cartItemRepository.saveAll(cartItems);
    }

    private void saveInvoice() {

        if (invoiceRepository.count() != 0) return;

        List<Invoice> invoices = new ArrayList<>();
        List<CartItem> cartItems = (List<CartItem>) cartItemRepository.findAll();
        Map<Cart, List<CartItem>> carts = cartItems.stream().collect(Collectors.groupingBy(CartItem::getCart));

        carts.keySet().forEach(cart -> {
            List<InvoiceItem> itemsInCard = carts.get(cart).stream()
                    .map(InvoiceItem::new).toList();
            Double totalPrice = itemsInCard.stream()
                    .mapToDouble(InvoiceItem::getPrice).reduce(0D, Double::sum);
            invoices.add(new Invoice(totalPrice, itemsInCard));
        });

        invoiceRepository.saveAll(invoices);
    }

    private void saveOrders() {

        if (orderRepository.count() != 0) return;

        List<Order> orders = new ArrayList<>();
        List<Cart> cartItems = cartRepository.findCartsWithPriceBetween(300D, 7000D);

        cartItems.forEach(cart -> {
            Order order = new Order(cart);
            order.setStatus(OrderStatus.values()[rand.nextInt(0, 3)]);
            orders.add(order);
        });
        orderRepository.saveAll(orders);
    }

    private List<Detail> getDetails(int amount) {

        List<Detail> details = new ArrayList<>();
        IntStream.range(0, amount).forEach(a -> {
            details.add(Detail.of(faker.stock().nsdqSymbol(), faker.stock().nyseSymbol()));
        });
        return details;
    }

    private Set<Category> getCategories(List<Category> categories, int amount, int offset) {
        Set<Category> result = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            Category nominee = categories.get(rand.nextInt(0, offset));
            if (!result.isEmpty() && result.contains(nominee)) {
                i--;
            } else {
                result.add(nominee);
            }
        }
        return result;
    }

    private LocalDateTime randomDate() {
        LocalDateTime start = LocalDateTime.now().minusYears(3);
        LocalDateTime end = LocalDateTime.now().plusMonths(1);
        return between(start, end);
    }

    private LocalDateTime between(LocalDateTime startInclusive, LocalDateTime endExclusive) {

        long startInstant = startInclusive.toInstant(ZoneOffset.UTC).getEpochSecond();
        long endInstant = endExclusive.toInstant(ZoneOffset.UTC).getEpochSecond();
        long randomSecond = ThreadLocalRandom
                .current()
                .nextLong(startInstant, endInstant);
        return LocalDateTime.ofEpochSecond(randomSecond, 1000, ZoneOffset.UTC);
    }
}
