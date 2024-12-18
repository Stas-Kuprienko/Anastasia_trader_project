package com.anastasia.smart_service.domain.subscription;

import com.anastasia.smart_service.Smart;
import io.grpc.stub.StreamObserver;

public interface SubscriptionService {

    StreamObserver<Smart.SubscribeRequest> submit(StreamObserver<Smart.SubscribeResponse> responseObserver);

    void submit(Smart.UnsubscribeRequest request, StreamObserver<Smart.UnsubscribeResponse> responseObserver);
}
