package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.AuditDataResponse;

public interface AuditDataService {
    AuditDataResponse auditRequest(String body);
}
