package com.delivery_admin.storeadmin.db.userordermenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderMenuStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private String description;
}
