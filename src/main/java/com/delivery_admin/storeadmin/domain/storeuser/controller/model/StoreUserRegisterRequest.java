package com.delivery_admin.storeadmin.domain.storeuser.controller.model;

import com.delivery_admin.storeadmin.db.storeuser.enums.StoreUserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserRegisterRequest {
    @NotBlank
    private String storeName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private StoreUserRole role;
}
