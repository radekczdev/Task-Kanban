package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    CreatorService creatorService;

    @Test
    public void shouldSendEmail() {
        // Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", null);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        MimeMessagePreparator mimeMessage = message -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(creatorService.build(mail.getMessage()), true);
        };

        // When
        simpleEmailService.send(mail, creatorService);

        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));

    }
}