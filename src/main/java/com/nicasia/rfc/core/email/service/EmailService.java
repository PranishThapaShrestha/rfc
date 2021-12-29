package com.nicasia.rfc.core.email.service;

import com.nicasia.rfc.core.email.Mail;

import java.util.List;

public interface EmailService {

    void pushEmails(List<Mail> emailRequest);

}

