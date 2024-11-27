package com.delivery_admin.storeadmin.domain.storeuser.business;

import com.delivery_admin.storeadmin.db.store.StoreEntity;
import com.delivery_admin.storeadmin.db.store.StoreRepository;
import com.delivery_admin.storeadmin.db.store.enums.StoreStatus;
import com.delivery_admin.storeadmin.db.storeuser.StoreUserEntity;
import com.delivery_admin.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import com.delivery_admin.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import com.delivery_admin.storeadmin.domain.storeuser.converter.StoreUserConverter;
import com.delivery_admin.storeadmin.domain.storeuser.service.StoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreUserBusiness {
    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;  // todo : 추후 service로 변경

    public StoreUserResponse register(StoreUserRegisterRequest request) {
        Optional<StoreEntity> storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED);
        StoreUserEntity entity = storeUserConverter.toEntity(request, storeEntity.get());
        StoreUserEntity newEntity = storeUserService.register(entity);
        StoreUserResponse response = storeUserConverter.toResponse(newEntity, storeEntity.get());

        return response;
    }
}
