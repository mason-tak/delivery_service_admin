package com.delivery_admin.storeadmin.domain.storemenu.service;

import com.delivery_admin.storeadmin.db.storemenu.StoreMenuEntity;
import com.delivery_admin.storeadmin.db.storemenu.StoreMenuRepository;
import com.delivery_admin.storeadmin.db.storemenu.enums.StoreMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERD)
                .orElseThrow(() -> new RuntimeException("Store menu not found"));
    }
}
