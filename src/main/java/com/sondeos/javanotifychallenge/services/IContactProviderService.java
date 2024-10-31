package com.sondeos.javanotifychallenge.services;

import com.sondeos.javanotifychallenge.providers.dto.ContactDto;

public interface IContactProviderService {
    public ContactDto getContactById(String type, String id, String message);

}
