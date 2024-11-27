package com.delivery_admin.storeadmin.domain.userordermenu.service;

import com.delivery_admin.storeadmin.db.userordermenu.UserOrderMenuEntity;
import com.delivery_admin.storeadmin.db.userordermenu.UserOrderMenuRepository;
import com.delivery_admin.storeadmin.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenuList(Long userOrderId) {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }
}
