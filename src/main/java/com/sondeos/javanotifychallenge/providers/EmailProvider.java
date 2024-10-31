package com.sondeos.javanotifychallenge.providers;

import com.sondeos.javanotifychallenge.providers.dto.NotifyPayloadDto;
import com.sondeos.javanotifychallenge.providers.dto.NotifyResultDto;
import com.sondeos.javanotifychallenge.services.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

/* Esta clase y sus métodos pueden ser modificados si se requiere */

@Component
public class EmailProvider implements IServiceProvider {
    @Value("${uri.email}")
    private String URL_EMAIL;

    @Autowired
    private RetryTemplate retryTemplate;

    @Retryable
    public NotifyResultDto notify(String destination, String message) {
        NotifyPayloadDto payload = new NotifyPayloadDto(destination, message);
        RestClient httpClient = RestClient.create();

        return retryTemplate.execute(context -> {
            return httpClient
                    .post()
                    .uri(URL_EMAIL)
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .retrieve()
                    .body(NotifyResultDto.class);
            }, context -> {
            if (context.getLastThrowable() instanceof HttpServerErrorException.InternalServerError) {
                throw (HttpServerErrorException.InternalServerError) context.getLastThrowable();
            }
            if (context.getLastThrowable() instanceof HttpClientErrorException.BadRequest) {
                throw (HttpClientErrorException.BadRequest) context.getLastThrowable();
            }
            return null;
        });
    }

}
