package com.sondeos.javanotifychallenge.providers;

import com.sondeos.javanotifychallenge.providers.dto.ContactDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

/* Esta clase y sus métodos pueden ser modificados si se requiere */

@Component
public class ContactProvider {
    @Value("${uri.contact}")
    private String URL_CONTACT;

    @Retryable
    public ContactDto getContact(String id) {
        RestClient httpClient = RestClient.create();

        try {
            return httpClient.get()
                    .uri(URL_CONTACT+id)
                    .retrieve()
                    .body(ContactDto.class);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw ex;
        }

    }

}
