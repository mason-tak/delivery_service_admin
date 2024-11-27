package com.delivery_admin.storeadmin.domain.storeuser.controller;

import com.delivery_admin.storeadmin.domain.storeuser.business.StoreUserBusiness;
import com.delivery_admin.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import com.delivery_admin.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-user")
public class StoreUserOpenApiController {
    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("")
    public StoreUserResponse register(
            @Valid @RequestBody StoreUserRegisterRequest request
    ) {
        return storeUserBusiness.register(request);
    }
}
