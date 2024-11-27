package com.delivery_admin.storeadmin.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {
    REGISTERED("등록중"),
    UNREGISTERED("해지");

    private String description;

}
