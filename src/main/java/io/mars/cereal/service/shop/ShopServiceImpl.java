package io.mars.cereal.service.shop;

import io.mars.cereal.data.shop.ShopRepository;
import io.mars.cereal.model.Shop;
import io.mars.cereal.model.exception.ContentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Iterable<Shop> saveAll(Iterable<Shop> shops) {
        return shopRepository.saveAll(shops);
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(()-> new ContentNotFound("no such shop found"));
    }

    @Override
    public Iterable<Shop> findAllById(Iterable<Long> ids) {
        return shopRepository.findAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        shopRepository.deleteAllById(ids);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}
