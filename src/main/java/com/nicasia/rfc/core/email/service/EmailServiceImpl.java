package com.nicasia.rfc.core.email.service;

import com.nicasia.rfc.core.email.Mail;
import com.nicasia.rfc.core.email.constant.EmailType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfiguration;


    private Object sendApproveSupportMail(Mail mail, EmailType emailType) {
        if (mail == null) {
            return null;
        }
        try {
            Mail mail1 = mail;
            sendSupportApproveMail(mail, emailType);
        } catch (TemplateException | IOException | MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendSupportApproveMail(Mail mail, EmailType emailType) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Template template = null;
        if (emailType.equals(EmailType.UPDATED)) {
            template = freeMarkerConfiguration.getTemplate("approved-supported-email.ftl");
        } else {
            template = freeMarkerConfiguration.getTemplate("approval-support-email.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setFrom(mail.getFrom());
        mailSender.send(mimeMessage);
    }


    @Async
    @Override
    public void pushMails(List<Mail> emailRequest) {

        if (emailRequest.size() == 0) {
            return;
        }
        emailRequest.stream()
                .map(mail -> sendApproveSupportMail(mail, EmailType.REQUESTED));
    }

    @Async
    @Override
    public void pushSupportedApprovedMails(Mail emailRequest) {
        sendApproveSupportMail(emailRequest, EmailType.UPDATED);
    }
}
