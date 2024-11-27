package com.delivery_admin.storeadmin.domain.userorder.converter;

import com.delivery_admin.storeadmin.db.userorder.UserOrderEntity;
import com.delivery_admin.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class UserOrderConverter {
    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity) {
        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
                .userId(userOrderEntity.getUserId())
                .storeId(userOrderEntity.getStoreId())
                .status(userOrderEntity.getStatus())
                .amount(userOrderEntity.getAmount())
                .orderedAt(userOrderEntity.getOrderedAt())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build();
    }
}
