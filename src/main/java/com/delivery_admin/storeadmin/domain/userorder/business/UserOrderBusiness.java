package com.delivery_admin.storeadmin.domain.userorder.business;

import com.delivery_admin.storeadmin.common.message.model.UserOrderMessage;
import com.delivery_admin.storeadmin.db.userordermenu.UserOrderMenuEntity;
import com.delivery_admin.storeadmin.domain.sse.connection.SseConnectionPool;
import com.delivery_admin.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;
import com.delivery_admin.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import com.delivery_admin.storeadmin.domain.storemenu.service.StoreMenuService;
import com.delivery_admin.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import com.delivery_admin.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import com.delivery_admin.storeadmin.domain.userorder.converter.UserOrderConverter;
import com.delivery_admin.storeadmin.domain.userorder.service.UserOrderService;
import com.delivery_admin.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final SseConnectionPool sseConnectionPool;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderConverter userOrderConverter;

    /**
     * 주문
     * 주문 내역 찾기
     * 스토어 찾기
     * 연결된 세션 찾기
     */
    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        var userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId()).orElseThrow(
                () -> new RuntimeException("사용자 주문내역 없음")
        );

        // user order menu
        List<UserOrderMenuEntity> userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        // user order menu -> store menu
        var storeMenuResponsesList = userOrderMenuList.stream()
                .map(userOrderMenuEntity -> {
                    return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId());
                })
                .map(storeMenuEntity -> {
                    return storeMenuConverter.toResponse(storeMenuEntity);
                }).toList();

        UserOrderResponse userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        // response
        var push = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponseList(storeMenuResponsesList)
                .build();

        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());

        // 사용자에게 push
        userConnection.sendMessage(push);
    }
}
