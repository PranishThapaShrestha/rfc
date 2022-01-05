//package com.nicasia.rfc.core.email.configurations;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Component;
//
//import java.util.Properties;
//
//@Component
//@Configuration
//public class EmailConfiguration {
//
//    @Value("$spring.mail.host")
//    private String host;
//
//    @Value("$spring.mail.port")
//    private String port;
//
//    @Value("$spring.mail.protocol")
//    private String protocol;
//
//
//    @Bean
//    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(host);
//        mailSender.setPort(Integer.parseInt(port));
////        mailSender.setUsername(emailUsername);
////        mailSender.setPassword(emailPassword);
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "false");
//        javaMailProperties.put("mail.smtp.auth", "false");
//        javaMailProperties.put("mail.smtp.ssl.enable", "false");
//        javaMailProperties.put("mail.transport.protocol", protocol);
//        javaMailProperties.put("mail.debug", "false");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }
//
//}
