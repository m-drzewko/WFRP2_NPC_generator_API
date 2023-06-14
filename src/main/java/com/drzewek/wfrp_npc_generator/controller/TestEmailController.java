package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/email-test")
@AllArgsConstructor
public class TestEmailController {

    private EmailSenderService emailSenderService;

    @GetMapping("/send")
    public void sendTestEmail() {
        emailSenderService.sendEmail("changeMe@gmail.com", "Test email from App", "This is a test email.");
    }
}
