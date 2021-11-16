package com.globallogic.userapi.services;

import com.globallogic.userapi.CustoExceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.User;

public interface AuditDataService {
    User auditRequest(String body) throws AuditDataServiceException;
}
