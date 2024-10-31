package com.sondeos.javanotifychallenge.services;

import com.sondeos.javanotifychallenge.constants.TypeOfNotification;
import com.sondeos.javanotifychallenge.providers.dto.ContactDto;
import com.sondeos.javanotifychallenge.repository.NotificationRepository;
import com.sondeos.javanotifychallenge.services.dto.NotificationProcessResult;
import com.sondeos.javanotifychallenge.utils.BatchProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotifyServiceAsync {
    @Autowired
    private BatchProcessor batchProcessor;

    @Autowired
    private INotifyService notifyService;

    @Autowired
    private IContactProviderService contactProviderService;

    public NotificationProcessResult processNotifications() {
        long startTime = System.currentTimeMillis();
        AtomicInteger processed = new AtomicInteger();
        AtomicInteger sent = new AtomicInteger();

        List<List<Map<String, String>>> contacts = batchProcessor.partitionList(NotificationRepository.getNotifications());
        contacts.parallelStream()
                .flatMap(b -> b.stream()
                        .map(n -> dispatchNotificationAsync(n.get("type"), n.get("contactId"), n.get("message"))
                                .thenApply(result -> {
                                    processed.getAndIncrement();
                                    if (result) {
                                        sent.getAndIncrement();
                                    }

                                    return result;
                                })
                        )
                )
                .toList();

        long duration = (System.currentTimeMillis() - startTime) / 1000;

        return new NotificationProcessResult(processed.get(), sent.get(), duration);
    }

    @Async
    public CompletableFuture<Boolean> dispatchNotificationAsync(String type, String contactId, String message) {
        if (!message.isEmpty()) {
            ContactDto contactDtoResponse = contactProviderService.getContactById(type, contactId, message);

            if (contactDtoResponse != null) {
                if (type.equalsIgnoreCase(TypeOfNotification.EMAIL.name())) {
                    // procesar email
                    return CompletableFuture.completedFuture(notifyService.notifyByEmail(contactDtoResponse.getEmail(), message));
                }

                if (type.equalsIgnoreCase(TypeOfNotification.SMS.name())) {
                    // procesar sms
                    return CompletableFuture.completedFuture(notifyService.notifyBySms(contactDtoResponse.getPhoneNumber(), message));
                }
            }

        }

        return CompletableFuture.completedFuture(false);
    }

}
