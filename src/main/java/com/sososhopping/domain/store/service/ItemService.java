package com.sososhopping.domain.store.service;

import com.sososhopping.common.exception.BadRequestException;
import com.sososhopping.common.exception.NotFoundException;
import com.sososhopping.domain.owner.repository.OwnerRepository;
import com.sososhopping.domain.store.dto.request.CreateItemDto;
import com.sososhopping.domain.store.dto.response.StoreItemDto;
import com.sososhopping.domain.store.repository.StoreRepository;
import com.sososhopping.entity.store.Item;
import com.sososhopping.entity.store.Store;
import com.sososhopping.domain.store.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final StoreService storeService;
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    public Long createItem(Long ownerId, Long storeId, CreateItemDto dto) {
        Store store = storeService.validateStoreOwner(ownerId, storeId);
        Item item = dto.toEntity(store);
        itemRepository.save(item);
        return item.getId();
    }

    public StoreItemDto findStoreItem(Long storeId, Long itemId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store not found with id " + storeId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + itemId));

        if (!item.belongsTo(store)) {
            throw new BadRequestException("item with id " + itemId + " does not belong to store with id " + storeId);
        }

        return new StoreItemDto(storeId, item);
    }

    public List<StoreItemDto> findStoreItems(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store not found with id " + storeId));

        return store.getItems().stream()
                .map(item -> new StoreItemDto(storeId, item))
                .collect(Collectors.toList());
    }
}
