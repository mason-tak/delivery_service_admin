package com.delivery_admin.storeadmin.domain.userorder.service;

import com.delivery_admin.storeadmin.db.userorder.UserOrderEntity;
import com.delivery_admin.storeadmin.db.userorder.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public Optional<UserOrderEntity> getUserOrder(Long id) {
        return userOrderRepository.findById(id);
    }
}
