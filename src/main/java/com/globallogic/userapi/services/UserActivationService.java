package com.globallogic.userapi.services;

import com.globallogic.userapi.customexceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.User;

public interface UserActivationService {
    User createRequest(String body) throws AuditDataServiceException;
}
