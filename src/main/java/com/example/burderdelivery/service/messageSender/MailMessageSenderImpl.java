package com.example.burderdelivery.service.messageSender;

import com.example.burderdelivery.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailMessageSenderImpl implements MessageSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String gmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        MessageDTO messageDTO = MessageDTO.builder()
                .to(gmail)
                .header("dwdwd")
                .body("dwdwwwwwwwwww")
                .build();
        simpleMailMessage.setTo(messageDTO.getTo());
        simpleMailMessage.setSubject(messageDTO.getHeader());
        simpleMailMessage.setText(messageDTO.getBody());
        javaMailSender.send(simpleMailMessage);
    }
}
