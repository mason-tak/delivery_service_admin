package com.delivery_admin.storeadmin.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지");

    private final String description;
}
