package com.sondeos.javanotifychallenge.services.impl;

import com.sondeos.javanotifychallenge.constants.TypeOfNotification;
import com.sondeos.javanotifychallenge.providers.ContactProvider;
import com.sondeos.javanotifychallenge.providers.dto.ContactDto;
import com.sondeos.javanotifychallenge.services.IContactProviderService;
import com.sondeos.javanotifychallenge.utils.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class ContactProviderServiceImpl implements IContactProviderService {
    @Autowired
    private ContactProvider provider;

    @Autowired
    private ValidationRules validation;

    @Override
    //@CachePut("contacts")
    public ContactDto getContactById(String type, String id, String message) {
        ContactDto contactResponse = null;

        try {
            contactResponse = provider.getContact(id);
        } catch (HttpClientErrorException.NotFound | HttpServerErrorException.InternalServerError ex) {
            return null;
        }

        if (type.equalsIgnoreCase(TypeOfNotification.EMAIL.name())) {
            return validation.validateEmailFormat(contactResponse.getEmail()) ? contactResponse : null;
        }

        return validation.validatePhoneNumberFormat(contactResponse.getPhoneNumber()) && validation.validateSmsMessage(message) ? contactResponse : null;
    }

}
