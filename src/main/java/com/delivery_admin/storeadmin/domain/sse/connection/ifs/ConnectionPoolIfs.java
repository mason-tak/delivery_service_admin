package com.delivery_admin.storeadmin.domain.sse.connection.ifs;

public interface ConnectionPoolIfs<T, R> {
    void addSession(T uniqueKey, R session);

    R getSession(T uniqueKey);

    void onCompletionCallback(R session);
}
