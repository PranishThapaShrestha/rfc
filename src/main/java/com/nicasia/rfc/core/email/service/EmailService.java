package com.nicasia.rfc.core.email.service;

import com.nicasia.rfc.core.email.Mail;

import java.util.List;

public interface EmailService {

    void pushMails(List<Mail> emailRequest);

    void pushSupportedApprovedMails(Mail emailRequest);

}

