package com.anastasia.smart_service.model;

import com.anastasia.smart_service.Smart;

import java.util.concurrent.ExecutorService;

public interface Drone {
    void run(ExecutorService executorService);

    void addSubscriber(Smart.Account account);

    void removeSubscriber(Smart.Account account);

    boolean isEmpty();

    void stop(boolean forcibly);
}
