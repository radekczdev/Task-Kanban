package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.DevInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbCreatorService implements CreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private DevInfo devInfo;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/radekczdev.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", devInfo.getCompanyName());
        context.setVariable("company_goal", devInfo.getCompanyGoal());
        context.setVariable("show_button", true);
        return templateEngine.process("mail/daily-report-mail", context);
    }
}
