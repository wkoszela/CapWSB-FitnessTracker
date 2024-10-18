package com.capgemini.wsb.fitnesstracker.mail.internal;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
class MailConfig {

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        var mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("sandbox.smtp.mailtrap.io");
//        mailSender.setPort(2525);
//        mailSender.setUsername("c7704f9468d082");
//        mailSender.setPassword("9c3d0bb8ad155b");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        return mailSender;
//    }

}
