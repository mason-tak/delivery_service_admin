package com.delivery_admin.storeadmin.domain.authorization;

import com.delivery_admin.storeadmin.db.store.StoreEntity;
import com.delivery_admin.storeadmin.db.store.StoreRepository;
import com.delivery_admin.storeadmin.db.store.enums.StoreStatus;
import com.delivery_admin.storeadmin.db.storeuser.StoreUserEntity;
import com.delivery_admin.storeadmin.domain.authorization.model.UserSession;
import com.delivery_admin.storeadmin.domain.storeuser.service.StoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StoreUserEntity> storeUserEntity = storeUserService.getRegisterUser(username);
        Optional<StoreEntity> storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                storeUserEntity.get().getStoreId(),
                StoreStatus.REGISTERED
        );

        return storeUserEntity.map(it -> {
            var userSession = UserSession.builder()
                    .userId(it.getId())
                    .email(it.getEmail())
                    .password(it.getPassword())
                    .status(it.getStatus())
                    .role(it.getRole())
                    .registeredAt(it.getRegisteredAt())
                    .lastLoginAt(it.getLastLoginAt())
                    .unregisteredAt(it.getUnregisteredAt())
                    .storeId(storeEntity.get().getId())
                    .storeName(storeEntity.get().getName())
                    .build();


            return userSession;
        }).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
    }
}
