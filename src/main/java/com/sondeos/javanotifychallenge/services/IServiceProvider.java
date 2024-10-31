package com.sondeos.javanotifychallenge.services;

import com.sondeos.javanotifychallenge.providers.dto.NotifyResultDto;

public interface IServiceProvider {
    public NotifyResultDto notify(String destination, String message);
}
